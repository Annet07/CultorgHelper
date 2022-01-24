package ru.itis.javalab.service;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.generator.HtmlGenerator;
import ru.itis.javalab.generator.PdfGenerator;
import ru.itis.javalab.generator.PdfGeneratorImpl;
import ru.itis.javalab.model.Application;
import ru.itis.javalab.model.InfoForApplication;
import ru.itis.javalab.repository.ApplicationRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

@Service
public class PdfServiceImpl implements PdfService{

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Override
    public String getPdfFileForUser(String message) throws IOException, DocumentException {
        String[] data = message.split("~~~");
        Gson gson = new Gson();
        InfoForApplication infoForApplication = gson.fromJson(data[1], InfoForApplication.class);
        Long user_id = Long.valueOf(data[0]);
        String pdfPath = pdfGenerator.getPdfFile(infoForApplication);
        applicationRepository.save(Application.builder()
                .date(new Date())
                .user_id(user_id)
                .path(pdfPath)
                .type("Concert_Application")
                .build());
        return pdfPath;
    }

}

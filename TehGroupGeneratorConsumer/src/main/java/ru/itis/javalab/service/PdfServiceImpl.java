package ru.itis.javalab.service;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.generator.PdfGenerator;
import ru.itis.javalab.model.Application;
import ru.itis.javalab.model.InfoForTehGroup;
import ru.itis.javalab.repository.ApplicationRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class PdfServiceImpl implements PdfService{

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    @Override
    public byte[] getPdfFileForUser(String message) throws IOException, DocumentException {
        String[] data = message.split("~~~");
        Gson gson = new Gson();
        InfoForTehGroup infoForApplication = gson.fromJson(data[1], InfoForTehGroup.class);
        Long user_id = Long.valueOf(data[0]);
        String pdfPath = pdfGenerator.getPdfFile(infoForApplication);
        byte[] array = Files.readAllBytes(Paths.get(pdfPath));
        applicationRepository.save(Application.builder()
                .date(new Date())
                .user_id(user_id)
                .type("Teh_Group")
                .build());
        return array;
    }
}

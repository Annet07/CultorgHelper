package ru.itis.javalab.service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.generator.PdfGenerator;
import ru.itis.javalab.model.InfoForTehGroup;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class PdfServiceImpl implements PdfService{

    @Autowired
    private PdfGenerator pdfGenerator;

    @Override
    public String getPdfFileForUser(InfoForTehGroup infoForTehGroup) throws IOException, DocumentException {
        return pdfGenerator.getPdfFile(infoForTehGroup);
    }
}
package ru.itis.javalab.service;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForConcertCostume;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfService {
    String getPdfFileForUser(InfoForConcertCostume infoForConcertCostume) throws IOException, DocumentException;
}

package ru.itis.javalab.service;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForNightSetup;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfService {
    String getPdfFileForUser(InfoForNightSetup infoForNightSetup) throws IOException, DocumentException;
}

package ru.itis.javalab.service;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForTehGroup;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfService {
    String getPdfFileForUser(InfoForTehGroup infoForTehGroup) throws IOException, DocumentException;
}

package ru.itis.javalab.service;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfService {

    String getPdfFileForUser(String message) throws IOException, DocumentException;
}

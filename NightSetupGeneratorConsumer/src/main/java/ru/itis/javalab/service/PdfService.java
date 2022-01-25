package ru.itis.javalab.service;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface PdfService {
    byte[] getPdfFileForUser(String message) throws IOException, DocumentException;
}

package ru.itis.javalab.generator;


import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfGenerator {

    String getPdfFile(InfoForApplication infoForApplication) throws IOException, DocumentException;
}

package ru.itis.javalab.generator;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForNightSetup;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfGenerator {

    String getPdfFile(InfoForNightSetup infoForNightSetup) throws IOException, DocumentException;
}

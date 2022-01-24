package ru.itis.javalab.generator;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForConcertCostume;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfGenerator {
    String getPdfFile(InfoForConcertCostume infoForConcertCostume) throws IOException, DocumentException;
}

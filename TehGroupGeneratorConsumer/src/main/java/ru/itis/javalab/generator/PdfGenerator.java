package ru.itis.javalab.generator;

import com.itextpdf.text.DocumentException;
import ru.itis.javalab.model.InfoForTehGroup;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfGenerator {
    String getPdfFile(InfoForTehGroup infoForTehGroup) throws IOException, DocumentException;
}

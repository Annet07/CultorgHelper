package ru.itis.javalab.generator;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.itis.javalab.model.InfoForConcertCostume;
import java.io.*;
import java.nio.file.Paths;

@Component
public class PdfGeneratorImpl implements PdfGenerator{

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Override
    public String getPdfFile(InfoForConcertCostume infoForConcertCostume) throws IOException, DocumentException {

        String name = htmlGenerator.getHtmlFile(infoForConcertCostume);
        File pdfPath = new File(name + ".pdf");
        File htmlFile = new File(name + ".html");
        String url = htmlFile.toURI().toURL().toString();
        OutputStream out = new FileOutputStream(pdfPath);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont(Paths.get("src", "main", "resources", "fonts", "timesnewromanpsmt.ttf").toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
        renderer.finishPDF();
        return pdfPath.getAbsolutePath();
    }
}

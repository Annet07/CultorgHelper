package ru.itis.javalab.generator;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.itis.javalab.model.InfoForApplication;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class PdfGeneratorImpl implements PdfGenerator{

    private final static Logger logger = LoggerFactory.getLogger(PdfGeneratorImpl.class);

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Override
    public String getPdfFile(InfoForApplication infoForApplication) throws IOException, DocumentException {

        String name = htmlGenerator.getHtmlFile(infoForApplication);
        File pdfFile = new File(name + ".pdf");
        File htmlFile = new File(name + ".html");
        String url = htmlFile.toURI().toURL().toString();
        OutputStream out = new FileOutputStream(pdfFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont(Paths.get("src","main", "resources", "fonts", "timesnewromanpsmt.ttf").toString(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
        Files.deleteIfExists(htmlFile.toPath());
        return pdfFile.getAbsolutePath();

    }
}

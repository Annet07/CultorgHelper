package ru.itis.javalab.generator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.io.FileUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.itis.javalab.model.InfoForApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class PdfGeneratorImpl implements PdfGenerator{

    private final static Logger logger = LoggerFactory.getLogger(PdfGeneratorImpl.class);

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Override
    public String getPdfFile(InfoForApplication infoForApplication) throws IOException, DocumentException {

        String name = htmlGenerator.getHtmlFile(infoForApplication);
        String pdfPath = "D:\\IDEA\\App_2021-2022\\Producer\\src\\main\\resources\\pdf\\";
        String htmlFileToString = "D:\\IDEA\\App_2021-2022\\ConcertApplicationGeneratorConsumer\\src\\main\\resources\\html\\" + name + ".html";
        String pdfFileToString = pdfPath + name + ".pdf";
        File file = new File(htmlFileToString);
        String url = file.toURI().toURL().toString();
        OutputStream out = new FileOutputStream(pdfFileToString);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("D:\\IDEA\\App_2021-2022\\ConcertApplicationGeneratorConsumer\\src\\main\\resources\\fonts\\timesnewromanpsmt.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
        return pdfFileToString;

    }
}

package ru.itis.javalab.generator;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.itis.javalab.model.InfoForConcertCostume;
import java.io.*;

@Component
public class PdfGeneratorImpl implements PdfGenerator{

    @Autowired
    private HtmlGenerator htmlGenerator;

    @Override
    public String getPdfFile(InfoForConcertCostume infoForConcertCostume) throws IOException, DocumentException {

        String name = htmlGenerator.getHtmlFile(infoForConcertCostume);
        String pdfPath = "D:\\IDEA\\App_2021-2022\\ConcertCostumeGeneratorConsumer\\src\\main\\resources\\pdf\\";
        String htmlFileToString = "D:\\IDEA\\App_2021-2022\\ConcertCostumeGeneratorConsumer\\src\\main\\resources\\html\\" + name + ".html";
        String pdfFileToString = pdfPath + name + ".pdf";
        File file = new File(htmlFileToString);
        String url = file.toURI().toURL().toString();
        OutputStream out = new FileOutputStream(pdfFileToString);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("D:\\IDEA\\App_2021-2022\\ConcertCostumeGeneratorConsumer\\src\\main\\resources\\fonts\\timesnewromanpsmt.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
        return pdfFileToString;
    }
}

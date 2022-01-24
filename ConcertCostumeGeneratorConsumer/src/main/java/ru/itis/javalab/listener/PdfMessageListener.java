package ru.itis.javalab.listener;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.InfoForConcertCostume;
import ru.itis.javalab.service.PdfService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class PdfMessageListener {

    private final static Logger logger = LoggerFactory.getLogger(PdfMessageListener.class);

    @Autowired
    private PdfService pdfService;

    @RabbitListener(queues = "concertCostumeQueue", containerFactory = "containerFactory")
    public Object onMessage(String message) throws IOException, DocumentException {
        logger.info(message);
        Gson gson = new Gson();
        String reply = pdfService.getPdfFileForUser(gson.fromJson(message, InfoForConcertCostume.class));
        logger.info(reply);
        return reply;
    }
}

package ru.itis.javalab.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.listeners.PdfMessageListener;
import ru.itis.javalab.model.InfoForApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class HtmlGeneratorImpl implements HtmlGenerator{

    private final Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);

    @Override
    public String getHtmlFile(InfoForApplication infoForApplication) throws IOException {
        configuration.setDirectoryForTemplateLoading(new File("D:\\IDEA\\App_2021-2022\\ConcertApplicationGeneratorConsumer\\src\\main\\resources\\templates"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template confirmMailTemplate;
        try{
            confirmMailTemplate = configuration.getTemplate("ConcertApplication.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String firstLetterCultorgName = infoForApplication.getCultorgName().charAt(0) + ".";
        String firstLetterCultorgPatronymic = infoForApplication.getCultorgPatronymic().charAt(0) + ".";
        String firstLetterDeputyName = infoForApplication.getDeputyName().charAt(0) + ".";
        String firstLetterDeputyPatronymic = infoForApplication.getDeputyPatronymic().charAt(0) + ".";

        Map<String, String> attributes = new HashMap<>();
        attributes.put("institute", infoForApplication.getInstitute());
        attributes.put("concert_date", infoForApplication.getConcertDate());
        attributes.put("cultorg_name", infoForApplication.getCultorgName());
        attributes.put("cultorg_surname", infoForApplication.getCultorgSurname());
        attributes.put("cultorg_patronymic", infoForApplication.getCultorgPatronymic());
        attributes.put("cultorg_class", infoForApplication.getCultorgClass());
        attributes.put("cultorg_phone", infoForApplication.getCultorgPhone());
        attributes.put("producer_name", infoForApplication.getProducerName());
        attributes.put("producer_surname", infoForApplication.getProducerSurname());
        attributes.put("producer_patronymic", infoForApplication.getProducerPatronymic());
        attributes.put("producer_class", infoForApplication.getProducerClass());
        attributes.put("producer_phone", infoForApplication.getProducerPhone());
        attributes.put("deputy_name", infoForApplication.getDeputyName());
        attributes.put("deputy_surname", infoForApplication.getDeputySurname());
        attributes.put("deputy_patronymic", infoForApplication.getDeputyPatronymic());
        attributes.put("deputy_phone", infoForApplication.getDeputyPhone());
        attributes.put("date_in_the_small_hall", infoForApplication.getDateInTheSmallHall());
        attributes.put("date_in_the_big_hall", infoForApplication.getDateInTheBigHall());
        attributes.put("time_in_the_small_hall", infoForApplication.getTimeInTheSmallHall());
        attributes.put("time_in_the_big_hall", infoForApplication.getTimeInTheBigHall());
        attributes.put("first_letter_cultorg_name", firstLetterCultorgName);
        attributes.put("first_letter_cultorg_patronymic", firstLetterCultorgPatronymic);
        attributes.put("first_letter_deputy_name", firstLetterDeputyName);
        attributes.put("first_letter_deputy_patronymic", firstLetterDeputyPatronymic);

        UUID name = UUID.randomUUID();
        String path = "D:\\IDEA\\App_2021-2022\\ConcertApplicationGeneratorConsumer\\src\\main\\resources\\html\\" + name + ".html";
        Writer writer = new OutputStreamWriter(new FileOutputStream(path));
        try {
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        return name.toString();
    }
}

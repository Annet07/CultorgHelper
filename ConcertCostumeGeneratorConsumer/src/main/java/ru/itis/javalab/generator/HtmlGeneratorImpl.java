package ru.itis.javalab.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.InfoForConcertCostume;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class HtmlGeneratorImpl implements HtmlGenerator{

    @Autowired
    private Configuration configuration;

    @Override
    public String getHtmlFile(InfoForConcertCostume infoForConcertCostume) throws IOException {
        Template confirmMailTemplate;
        try{
            confirmMailTemplate = configuration.getTemplate("ConcertCostume.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String firstLetterDeputyName = infoForConcertCostume.getDeputyName().charAt(0) + ".";
        String firstLetterDeputyPatronymic = infoForConcertCostume.getDeputyPatronymic().charAt(0) + ".";

        Map<String, String> attributes = new HashMap<>();
        attributes.put("institute", infoForConcertCostume.getInstitute());
        attributes.put("concert_date", infoForConcertCostume.getConcertDate());
        attributes.put("cultorg_name", infoForConcertCostume.getCultorgName());
        attributes.put("cultorg_surname", infoForConcertCostume.getCultorgSurname());
        attributes.put("cultorg_patronymic", infoForConcertCostume.getCultorgPatronymic());
        attributes.put("deputy_surname", infoForConcertCostume.getDeputySurname());
        attributes.put("first_letter_deputy_name", firstLetterDeputyName);
        attributes.put("first_letter_deputy_patronymic", firstLetterDeputyPatronymic);

        UUID name = UUID.randomUUID();
        String path = name + ".html";
        Writer writer = new OutputStreamWriter(new FileOutputStream(path));
        try {
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        return name.toString();
    }
}

package ru.itis.javalab.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.InfoForTehGroup;

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
    public String getHtmlFile(InfoForTehGroup infoForTehGroup) throws IOException {
        Template confirmMailTemplate;
        try{
            confirmMailTemplate = configuration.getTemplate("TehGroup.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String firstLetterDirectorName= infoForTehGroup.getDirectorName().charAt(0) + ".";
        String firstLetterDirectorPatronymic = infoForTehGroup.getDirectorPatronymic().charAt(0) + ".";

        Map<String, String> attributes = new HashMap<>();
        attributes.put("institute", infoForTehGroup.getInstitute());
        attributes.put("start_date", infoForTehGroup.getStartDate());
        attributes.put("start_time", infoForTehGroup.getStartTime());
        attributes.put("finish_date", infoForTehGroup.getFinishDate());
        attributes.put("finish_time", infoForTehGroup.getFinishTime());
        attributes.put("responsible_name", infoForTehGroup.getResponsibleName());
        attributes.put("responsible_surname", infoForTehGroup.getResponsibleSurname());
        attributes.put("responsible_patronymic", infoForTehGroup.getResponsiblePatronymic());
        attributes.put("responsible_position", infoForTehGroup.getResponsiblePosition());
        attributes.put("institute_in_the_genitive", infoForTehGroup.getInstituteInTheGenitive());
        attributes.put("first_letter_director_name", firstLetterDirectorName);
        attributes.put("first_letter_director_patronymic", firstLetterDirectorPatronymic);
        attributes.put("director_surname", infoForTehGroup.getDirectorSurname());
        attributes.put("today", infoForTehGroup.getToday());

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

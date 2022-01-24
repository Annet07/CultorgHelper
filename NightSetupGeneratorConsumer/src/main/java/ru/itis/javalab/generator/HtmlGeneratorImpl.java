package ru.itis.javalab.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.model.InfoForNightSetup;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class HtmlGeneratorImpl implements HtmlGenerator{

    @Autowired
    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);

    @Override
    public String getHtmlFile(InfoForNightSetup infoForNightSetup) throws IOException {
        configuration.setDirectoryForTemplateLoading(new File("D:\\IDEA\\App_2021-2022\\NightSetupGeneratorConsumer\\src\\main\\resources\\templates"));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Template confirmMailTemplate;
        try{
            confirmMailTemplate = configuration.getTemplate("NightSetup.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String firstLetterCultorgName = infoForNightSetup.getCultorgName().charAt(0) + ".";
        String firstLetterCultorgPatronymic = infoForNightSetup.getCultorgPatronymic().charAt(0) + ".";
        String firstLetterDeputyName = infoForNightSetup.getDeputyName().charAt(0) + ".";
        String firstLetterDeputyPatronymic = infoForNightSetup.getDeputyPatronymic().charAt(0) + ".";

        Map<String, String> attributes = new HashMap<>();
        attributes.put("institute", infoForNightSetup.getInstitute());
        attributes.put("concert_date", infoForNightSetup.getConcertDate());
        attributes.put("start_date", infoForNightSetup.getStartDate());
        attributes.put("start_time", infoForNightSetup.getStartTime());
        attributes.put("finish_date", infoForNightSetup.getFinishDate());
        attributes.put("finish_time", infoForNightSetup.getFinishTime());
        attributes.put("responsible_name", infoForNightSetup.getResponsibleName());
        attributes.put("responsible_surname", infoForNightSetup.getResponsibleSurname());
        attributes.put("responsible_patronymic", infoForNightSetup.getResponsiblePatronymic());
        attributes.put("responsible_position", infoForNightSetup.getResponsiblePosition());
        attributes.put("responsible_phone", infoForNightSetup.getResponsiblePhone());
        attributes.put("cultorg_surname", infoForNightSetup.getCultorgSurname());
        attributes.put("deputy_surname", infoForNightSetup.getDeputySurname());
        attributes.put("institute_in_the_genitive", infoForNightSetup.getInstituteInTheGenitive());
        attributes.put("first_letter_cultorg_name", firstLetterCultorgName);
        attributes.put("first_letter_cultorg_patronymic", firstLetterCultorgPatronymic);
        attributes.put("first_letter_deputy_name", firstLetterDeputyName);
        attributes.put("first_letter_deputy_patronymic", firstLetterDeputyPatronymic);

        UUID name = UUID.randomUUID();
        String path = "D:\\IDEA\\App_2021-2022\\NightSetupGeneratorConsumer\\src\\main\\resources\\html\\" + name + ".html";
        Writer writer = new OutputStreamWriter(new FileOutputStream(path));
        try {
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        return name.toString();
    }
}

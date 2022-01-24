package ru.itis.javalab.generator;

import ru.itis.javalab.model.InfoForConcertCostume;

import java.io.IOException;

public interface HtmlGenerator {

    String getHtmlFile(InfoForConcertCostume infoForConcertCostume) throws IOException;
}

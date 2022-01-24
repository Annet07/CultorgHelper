package ru.itis.javalab.generator;

import ru.itis.javalab.model.InfoForNightSetup;

import java.io.IOException;

public interface HtmlGenerator {

    String getHtmlFile(InfoForNightSetup infoForNightSetup) throws IOException;
}

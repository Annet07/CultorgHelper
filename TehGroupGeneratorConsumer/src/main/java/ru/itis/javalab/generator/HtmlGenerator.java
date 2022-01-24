package ru.itis.javalab.generator;

import ru.itis.javalab.model.InfoForTehGroup;

import java.io.IOException;

public interface HtmlGenerator {
    String getHtmlFile(InfoForTehGroup infoForTehGroup) throws IOException;
}

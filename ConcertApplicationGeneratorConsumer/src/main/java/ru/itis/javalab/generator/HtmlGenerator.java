package ru.itis.javalab.generator;


import ru.itis.javalab.model.InfoForApplication;

import java.io.IOException;
import java.io.StringWriter;

public interface HtmlGenerator {

    String getHtmlFile(InfoForApplication infoForApplication) throws IOException;
}

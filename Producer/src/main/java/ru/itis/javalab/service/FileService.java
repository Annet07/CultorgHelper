package ru.itis.javalab.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    String saveFile(byte[] array) throws IOException;
}

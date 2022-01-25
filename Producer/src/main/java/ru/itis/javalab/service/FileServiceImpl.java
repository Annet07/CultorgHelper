package ru.itis.javalab.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String saveFile(byte[] array) throws IOException {
        UUID name = UUID.randomUUID();
        File path = Paths.get("src", "main", "resources", "pdf", name + ".pdf").toFile();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        bos.write(array);
        bos.flush();
        bos.close();
        return path.getAbsolutePath();
    }
}

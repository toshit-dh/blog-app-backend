package tech.toshitworks.blog_app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.toshitworks.blog_app.service.PostImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PostImageServiceImpl implements PostImageService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String generatedFileName = uuid.concat(fileName.substring(fileName.lastIndexOf('.')));
        String filePath = path + File.separator + generatedFileName;
        File folder = new File(path);
        if (!folder.exists()) folder.mkdir();
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

    @Override
    public InputStream getImage(String path, String filename) throws FileNotFoundException {
        String fullPath = path + File.separator + filename;
        return new FileInputStream(fullPath);
    }
}

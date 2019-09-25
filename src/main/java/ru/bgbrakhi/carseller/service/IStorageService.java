package ru.bgbrakhi.carseller.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    void init();
    String store(MultipartFile file);
    Path load(String filename);
    Resource loadAsResource(String filename);
}

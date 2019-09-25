package ru.bgbrakhi.carseller.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Repository
public class StorageServiceImpl implements IStorageService {
    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private static final String UPLOAD_DIRECTORY ="image_upload";
    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl() {
        this.rootLocation = Paths.get(UPLOAD_DIRECTORY);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            logger.error("Could not initialize storage", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String filename = "";
        if (!file.isEmpty()) {
            filename = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                if (filename.contains("..")) {
                    logger.error("Cannot store file with relative path outside current directory " + filename);
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, this.rootLocation.resolve(filename),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            }
            catch (IOException e) {
                logger.error("Failed to store file " + filename, e);
            }
        }
        return filename;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                logger.error("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            logger.error("Could not read file: " + filename, e);
        }
        return null;
    }
}

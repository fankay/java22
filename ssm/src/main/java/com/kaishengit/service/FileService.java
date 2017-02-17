package com.kaishengit.service;

import java.io.InputStream;

public interface FileService {
    String uploadFile(String originalFilename, String contentType, InputStream inputStream);
}

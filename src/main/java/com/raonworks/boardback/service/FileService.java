package com.raonworks.boardback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  String upload(MultipartFile files);
  Resource getImage(String fileName);

}

package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

  @Value("${file.path}")
  private String filePath;

  @Value("${file.url}")
  private String fileUrl;

  @Override
  public String upload(MultipartFile file) {
    if(file.isEmpty()) return null;

    String originalFilename = file.getOriginalFilename();
    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    String uuid = UUID.randomUUID().toString();
    String saveFileName = uuid + extension;
    String savePath = filePath + saveFileName;
    System.out.println(savePath);

    try {
      file.transferTo(new File(savePath));
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }

    return (fileUrl + saveFileName);
  }

  @Override
  public Resource getImage(String fileName) {
    Resource resource = null;

    try {
      resource = new UrlResource("file:" + filePath + fileName);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
    return resource;
  }
}

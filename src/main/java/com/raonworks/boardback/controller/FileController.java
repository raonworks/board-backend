package com.raonworks.boardback.controller;

import com.raonworks.boardback.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

  private final FileService fileService;

  // 이미지 업로드
  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile file) {
    String url = fileService.upload(file);
    return url;
  }

  // 이미지 다운로드
  @GetMapping(value = "{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  public Resource getImage(@PathVariable String fileName) {
    Resource resource = fileService.getImage(fileName);
    return resource;
  }
}

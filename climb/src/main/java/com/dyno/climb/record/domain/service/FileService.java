package com.dyno.climb.record.domain.service;

import com.dyno.climb.record.application.port.PreSignedUrlPort;
import com.dyno.climb.record.application.service.FileUseCase;
import com.dyno.climb.record.application.service.dto.PreSignedUrlDto;
import com.dyno.climb.user.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FileService implements FileUseCase {

  private final PreSignedUrlPort preSignedUrlPort;

  public FileService(PreSignedUrlPort preSignedUrlPort) {
    this.preSignedUrlPort = preSignedUrlPort;
  }

  @Override
  public PreSignedUrlDto generateUploadPreSignedUrl(User user, String fileName) {
    String filePath =
        "users/" + user.getAccountId() + "/records/" + LocalDate.now() + "-" + fileName;
    String preSignedUrl = preSignedUrlPort.generateUploadPreSignedUrl(filePath);

    return new PreSignedUrlDto(preSignedUrl, filePath);
  }

  @Override
  public String generatePreSignedUrl(String filePath) {
    return preSignedUrlPort.generateDownloadPreSignedUrl(filePath);
  }

  @Override
  public boolean fileExists(String filePath) {
    return preSignedUrlPort.fileExists(filePath);
  }
}

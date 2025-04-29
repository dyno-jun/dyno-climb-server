package com.dyno.climb.record.application.service;

import com.dyno.climb.record.application.service.dto.PreSignedUrlDto;
import com.dyno.climb.user.domain.User;

public interface FileUseCase {
  PreSignedUrlDto generateUploadPreSignedUrl(User user, String fileName);

  String generatePreSignedUrl(String filePath);

  boolean fileExists(String filePath);
}

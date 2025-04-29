package com.dyno.climb.record.application.port;

public interface PreSignedUrlPort {
  String generateUploadPreSignedUrl(String path);

  String generateDownloadPreSignedUrl(String path);

  boolean fileExists(String path);
}

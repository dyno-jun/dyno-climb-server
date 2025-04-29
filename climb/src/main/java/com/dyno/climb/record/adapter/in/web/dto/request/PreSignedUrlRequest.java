package com.dyno.climb.record.adapter.in.web.dto.request;

public class PreSignedUrlRequest {
  private String fileName;

  public PreSignedUrlRequest(String fileName) {
    this.fileName = fileName;
  }

  public PreSignedUrlRequest() {}

  public String getFileName() {
    return fileName;
  }
}

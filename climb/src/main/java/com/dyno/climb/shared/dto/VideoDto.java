package com.dyno.climb.shared.dto;


public class VideoDto {
  private String url;

  public VideoDto(String url) {
    this.url = url;
  }

  public VideoDto() {}

  public static VideoDto create(String url) {
    return new VideoDto(url);
  }

  public String getUrl() {
    return url;
  }
}

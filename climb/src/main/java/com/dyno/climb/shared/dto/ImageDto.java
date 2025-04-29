package com.dyno.climb.shared.dto;


public class ImageDto {
  private String url;

  public ImageDto(String url) {
    this.url = url;
  }

  public ImageDto() {}

  public static ImageDto create(String url) {
    return new ImageDto(url);
  }

  public String getUrl() {
    return url;
  }
}

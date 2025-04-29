package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import com.dyno.climb.shared.vo.Image;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ImageDto {
  @Column(name = "image_url")
  private String url;

  public ImageDto() {}

  public ImageDto(String url) {
    this.url = url;
  }

  public Image asDomain() {
    return new Image(url);
  }

  public String getUrl() {
    return url;
  }
}

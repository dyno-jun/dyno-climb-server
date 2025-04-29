package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import com.dyno.climb.gym.domain.vo.Link;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LinkDto {
  @Column(name = "link_url")
  private String url;

  public LinkDto() {}

  public Link asDomain() {
    return new Link(url);
  }

  public String getUrl() {
    return url;
  }
}

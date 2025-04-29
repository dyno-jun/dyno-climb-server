package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import com.dyno.climb.gym.domain.vo.Tel;
import jakarta.persistence.Embeddable;

@Embeddable
public class TelDto {
  private String phone;
  private String store;

  public TelDto() {}

  public Tel asDomain() {
    return new Tel(phone, store);
  }

  public String getPhone() {
    return phone;
  }

  public String getStore() {
    return store;
  }
}

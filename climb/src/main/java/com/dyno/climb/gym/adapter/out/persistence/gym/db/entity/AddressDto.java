package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import com.dyno.climb.gym.domain.vo.Address;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressDto {
  private String main;
  private String detail;
  private String postCode;

  public AddressDto() {}

  public Address asDomain() {
    return new Address(main, detail, postCode);
  }

  public String getMain() {
    return main;
  }

  public String getDetail() {
    return detail;
  }

  public String getPostCode() {
    return postCode;
  }
}

package com.dyno.climb.gym.adapter.in.web.dto;

import java.util.List;

public class GymResponse {
  private List<GymDto> gyms;

  public GymResponse() {}

  public GymResponse(List<GymDto> gyms) {
    this.gyms = gyms;
  }

  public List<GymDto> getGyms() {
    return gyms;
  }
}

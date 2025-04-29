package com.dyno.climb.mission.adapter.in.web.dto.request;

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;

public class MissionGroupSearchFilter extends SearchFilter {
  private String gymId;

  public MissionGroupSearchFilter(String keyword, Double lat, Double lng, String gymId) {
    super(keyword, lat, lng);
    this.gymId = gymId;
  }

  public String getGymId() {
    return gymId == null ? "" : gymId;
  }

  public void setGymId(String gymId) {
    this.gymId = gymId;
  }
}

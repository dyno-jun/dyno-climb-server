package com.dyno.climb.mission.adapter.in.web.dto.request;


public class MissionSearchFilter extends MissionGroupSearchFilter {
  private String missionGroupId;

  public MissionSearchFilter(
      String keyword, Double lat, Double lng, String gymId, String missionGroupId) {
    super(keyword, lat, lng, gymId);
    this.missionGroupId = missionGroupId;
  }

  public String getMissionGroupId() {
    return missionGroupId;
  }
}

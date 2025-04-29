package com.dyno.climb.record.adapter.in.web.dto.request;

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;

public class RecordSearchFilter extends SearchFilter {
  private String missionGroupId;
  private String missionId;

  public RecordSearchFilter() {}

  public RecordSearchFilter(
      String keyword, Double lat, Double lng, String missionGroupId, String missionId) {
    super(keyword, lat, lng);
    this.missionGroupId = missionGroupId;
    this.missionId = missionId;
  }

  public String getMissionGroupId() {
    return missionGroupId;
  }

  public void setMissionGroupId(String missionGroupId) {
    this.missionGroupId = missionGroupId;
  }

  public String getMissionId() {
    return missionId;
  }

  public void setMissionId(String missionId) {
    this.missionId = missionId;
  }

  @Override
  public String getQueryString() {
    return super.getQueryString()
        + "&missionId="
        + getMissionId()
        + "&missionGroupId="
        + getMissionGroupId();
  }
}

package com.dyno.climb.record.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotNull;

public class SubmitMissionRecordDto {

  @NotNull private String missionGroupId;

  @NotNull private String missionId;

  public SubmitMissionRecordDto() {}

  public SubmitMissionRecordDto(String missionGroupId, String missionId) {
    this.missionGroupId = missionGroupId;
    this.missionId = missionId;
  }

  public String getMissionGroupId() {
    return missionGroupId;
  }

  public String getMissionId() {
    return missionId;
  }
}

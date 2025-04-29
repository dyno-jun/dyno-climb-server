package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import com.dyno.climb.mission.domain.vo.UserMission;
import jakarta.persistence.*;

@Entity(name = "user_missions")
public class UserMissionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  private String userId;

  private String missionGroupId;

  private String missionId;

  private UserRecordSummaryDto summary;

  public UserMissionEntity() {}

  public UserMissionEntity(
      String uid,
      String userId,
      String missionGroupId,
      String missionId,
      UserRecordSummaryDto summary) {
    this.id = 0L;
    this.uid = uid;
    this.userId = userId;
    this.missionGroupId = missionGroupId;
    this.missionId = missionId;
    this.summary = summary;
  }

  public static UserMissionEntity of(UserMission userMission) {
    return new UserMissionEntity(
        userMission.getId(),
        userMission.getUserId(),
        userMission.getMissionGroupId(),
        userMission.getMissionId(),
        new UserRecordSummaryDto(
            userMission.getSummary().getTryCnt(), userMission.getSummary().getStatus()));
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getUserId() {
    return userId;
  }

  public String getMissionGroupId() {
    return missionGroupId;
  }

  public String getMissionId() {
    return missionId;
  }

  public UserRecordSummaryDto getSummary() {
    return summary;
  }

  public void setSummary(UserRecordSummaryDto summary) {
    this.summary = summary;
  }
}

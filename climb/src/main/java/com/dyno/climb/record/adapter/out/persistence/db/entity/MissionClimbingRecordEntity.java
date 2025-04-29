package com.dyno.climb.record.adapter.out.persistence.db.entity;

import com.dyno.climb.record.domain.MissionRecordStatus;
import jakarta.persistence.*;

@Entity(name = "mission_climbing_records")
public class MissionClimbingRecordEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String uid;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id", referencedColumnName = "uid")
  private ClimbingRecordEntity record;

  private String missionGroupId;
  private String missionId;

  @Enumerated(value = EnumType.STRING)
  private MissionRecordStatus status;

  public MissionClimbingRecordEntity() {}

  public MissionClimbingRecordEntity(
      Long id,
      String uid,
      ClimbingRecordEntity record,
      String missionGroupId,
      String missionId,
      MissionRecordStatus status) {
    this.id = id;
    this.uid = uid;
    this.record = record;
    this.missionGroupId = missionGroupId;
    this.missionId = missionId;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public ClimbingRecordEntity getRecord() {
    return record;
  }

  public String getMissionId() {
    return missionId;
  }

  public String getMissionGroupId() {
    return missionGroupId;
  }

  public MissionRecordStatus getStatus() {
    return status;
  }

  public void setStatus(MissionRecordStatus status) {
    this.status = status;
  }
}

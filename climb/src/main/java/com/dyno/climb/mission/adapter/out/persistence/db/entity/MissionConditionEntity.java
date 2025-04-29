package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import com.dyno.climb.mission.domain.vo.ConditionType;
import com.dyno.climb.mission.domain.vo.InEquality;
import jakarta.persistence.*;

@Entity(name = "mission_conditions")
public class MissionConditionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  private String missionId;

  @Enumerated(value = EnumType.STRING)
  private InEquality inequality;

  @Enumerated(value = EnumType.STRING)
  private ConditionType type;

  @Column(name = "mission_value")
  private int value;

  public MissionConditionEntity() {}

  public MissionConditionEntity(
      Long id, String uid, String missionId, InEquality inequality, ConditionType type, int value) {
    this.id = id;
    this.uid = uid;
    this.missionId = missionId;
    this.inequality = inequality;
    this.type = type;
    this.value = value;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getMissionId() {
    return missionId;
  }

  public InEquality getInequality() {
    return inequality;
  }

  public ConditionType getType() {
    return type;
  }

  public int getValue() {
    return value;
  }
}

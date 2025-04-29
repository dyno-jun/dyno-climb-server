package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.ImageDto;
import com.dyno.climb.mission.domain.vo.MissionType;
import jakarta.persistence.*;

@Entity(name = "missions")
public class MissionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  private String missionGroupId;

  private String boulderProblemId;
  private String title;
  private String description;

  @Enumerated(value = EnumType.STRING)
  private MissionType type;

  private int exp;

  @Embedded private ImageDto image;

  public MissionEntity() {}

  public MissionEntity(
      Long id,
      String uid,
      String missionGroupId,
      String boulderProblemId,
      String title,
      String description,
      MissionType type,
      int exp,
      ImageDto image) {
    this.id = id;
    this.uid = uid;
    this.missionGroupId = missionGroupId;
    this.boulderProblemId = boulderProblemId;
    this.title = title;
    this.description = description;
    this.type = type;
    this.exp = exp;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getMissionGroupId() {
    return missionGroupId;
  }

  public String getBoulderProblemId() {
    return boulderProblemId;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public MissionType getType() {
    return type;
  }

  public int getExp() {
    return exp;
  }

  public ImageDto getImage() {
    return image;
  }
}

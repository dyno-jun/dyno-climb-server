package com.dyno.climb.mission.adapter.out.persistence.db.entity;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.ImageDto;
import com.dyno.climb.mission.domain.vo.MissionGroupType;
import jakarta.persistence.*;

@Entity(name = "mission_groups")
public class MissionGroupEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  private String gymId;

  private String title;
  private String description;
  @Embedded private ImageDto image;

  @Embedded private MissionPeriodDto period;

  @Enumerated(value = EnumType.STRING)
  private MissionGroupType type;

  private int totalExp;

  public MissionGroupEntity() {}

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getGymId() {
    return gymId;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public ImageDto getImage() {
    return image;
  }

  public MissionGroupType getType() {
    return type;
  }

  public int getTotalExp() {
    return totalExp;
  }

  public MissionPeriodDto getPeriod() {
    return period;
  }
}

package com.dyno.climb.gym.adapter.out.persistence.boulderproblem.db.entity;

import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.BoulderLevelEntity;
import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.GymEntity;
import com.dyno.climb.gym.adapter.out.persistence.gym.db.entity.ImageDto;
import jakarta.persistence.*;

@Entity(name = "boulder_problems")
public class BoulderProblemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gym_id")
  private GymEntity gym;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "level_id")
  private BoulderLevelEntity level;

  private String sector;

  @Embedded private ImageDto image;

  public BoulderProblemEntity() {}

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public GymEntity getGym() {
    return gym;
  }

  public BoulderLevelEntity getLevel() {
    return level;
  }

  public String getSector() {
    return sector;
  }

  public ImageDto getImage() {
    return image;
  }
}

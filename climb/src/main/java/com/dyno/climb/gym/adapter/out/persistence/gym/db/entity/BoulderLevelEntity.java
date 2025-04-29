package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import com.dyno.climb.gym.domain.vo.BoulderLevel;
import jakarta.persistence.*;

@Entity(name = "boulder_levels")
public class BoulderLevelEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gym_id")
  private GymEntity gym;

  private String name;
  private String color;

  public BoulderLevelEntity() {}

  public BoulderLevel asDomain() {
    return new BoulderLevel(getUid(), getName(), getColor());
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public GymEntity getGym() {
    return gym;
  }
}

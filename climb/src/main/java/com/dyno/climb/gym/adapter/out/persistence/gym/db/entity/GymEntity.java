package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "gyms")
public class GymEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  private String name;

  @Embedded private AddressDto address;
  @Embedded private LocationDto location;
  @Embedded private TelDto tel;
  @Embedded private ImageDto image;
  @Embedded private LinkDto link;

  @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BoulderLevelEntity> levels;

  private LocalDateTime updatedAt;

  public GymEntity() {}

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getName() {
    return name;
  }

  public AddressDto getAddress() {
    return address;
  }

  public LocationDto getLocation() {
    return location;
  }

  public TelDto getTel() {
    return tel;
  }

  public ImageDto getImage() {
    return image;
  }

  public LinkDto getLink() {
    return link;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public List<BoulderLevelEntity> getLevels() {
    return levels;
  }
}

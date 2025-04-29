package com.dyno.climb.gym.adapter.in.web.dto;

import com.dyno.climb.gym.domain.Gym;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GymDto {
  private String id;
  private String name;
  private String address;
  private Double latitude;
  private Double longitude;
  private String tel;
  private String image;
  private String link;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  private List<BoulderLevelDto> levels;

  public static GymDto create(String id) {
    return new GymDto(
        id,
        "name",
        "address",
        37.1234,
        127.1234,
        "02-123-44567",
        "image",
        "link",
        LocalDateTime.now(),
        List.of(new BoulderLevelDto("초록", "#00FF00")));
  }

  public static GymDto of(Gym gym) {
    return new GymDto(
        gym.getId(),
        gym.getName(),
        gym.getAddress().main(),
        gym.getLocation().latitude(),
        gym.getLocation().longitude(),
        gym.getTel().phone(),
        gym.getImage().url(),
        gym.getLink().url(),
        gym.getUpdatedAt(),
        gym.getLevels().stream().map(BoulderLevelDto::of).collect(Collectors.toList()));
  }
}

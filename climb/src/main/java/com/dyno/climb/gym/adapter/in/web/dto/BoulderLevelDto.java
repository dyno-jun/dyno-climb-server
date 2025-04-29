package com.dyno.climb.gym.adapter.in.web.dto;

import com.dyno.climb.gym.domain.vo.BoulderLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoulderLevelDto {
  private String name;
  private String color;

  public static BoulderLevelDto of(BoulderLevel level) {
    return new BoulderLevelDto(level.name(), level.color());
  }
}

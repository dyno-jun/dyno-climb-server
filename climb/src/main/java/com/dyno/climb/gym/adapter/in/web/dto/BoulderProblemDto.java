package com.dyno.climb.gym.adapter.in.web.dto;

import com.dyno.climb.gym.domain.BoulderProblem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BoulderProblemDto {

  private String id;
  private String sector;
  private BoulderLevelDto level;
  private String image;
  private String gymId;

  public static BoulderProblemDto create() {
    return new BoulderProblemDto(
        "id", "sector", new BoulderLevelDto("초록", "#00FF00"), "image", "gymId");
  }

  public static BoulderProblemDto of(BoulderProblem problem) {
    return new BoulderProblemDto(
        problem.getId(),
        problem.getSector(),
        BoulderLevelDto.of(problem.getLevel()),
        problem.getImage().url(),
        problem.getGymId());
  }
}

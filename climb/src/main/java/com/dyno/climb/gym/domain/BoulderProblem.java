package com.dyno.climb.gym.domain;

import com.dyno.climb.gym.domain.vo.BoulderLevel;
import com.dyno.climb.shared.vo.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoulderProblem {
  private final String id;
  private String gymId;
  private String sector;
  private BoulderLevel level;
  private Image image;

  public static BoulderProblem create() {
    return new BoulderProblem(
        "boulderProblemId",
        "gymId",
        "sector",
        new BoulderLevel("id", "초록", "#00FF00"),
        new Image("image"));
  }
}

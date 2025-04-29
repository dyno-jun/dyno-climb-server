package com.dyno.climb.gym.adapter.in.web.dto;

import java.util.List;

public class BoulderProblemResponse {
  private List<BoulderProblemDto> boulderProblems;

  public BoulderProblemResponse() {}

  public BoulderProblemResponse(List<BoulderProblemDto> boulderProblems) {
    this.boulderProblems = boulderProblems;
  }

  public List<BoulderProblemDto> getBoulderProblems() {
    return boulderProblems;
  }
}

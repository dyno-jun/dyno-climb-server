package com.dyno.climb.gym.adapter.in.web.dto.request;

public class BoulderProblemFilter implements QueryParams {

  private String gymId;
  private String keyword;

  public BoulderProblemFilter(String gymId, String keyword) {
    this.gymId = gymId;
    this.keyword = keyword;
  }

  public String getGymId() {
    return gymId;
  }

  public String getKeyword() {
    return keyword == null ? "" : keyword;
  }

  @Override
  public String getQueryString() {
    return "?gymId=" + getGymId() + "&keyword=" + getKeyword();
  }
}

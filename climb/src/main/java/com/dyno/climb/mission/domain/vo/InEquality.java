package com.dyno.climb.mission.domain.vo;

public enum InEquality {
  EQ("="),
  GT(">="),
  LT("<=");

  private String value;

  InEquality(String value) {
    this.value = value;
  }
}

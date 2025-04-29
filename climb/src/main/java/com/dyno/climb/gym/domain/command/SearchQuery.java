package com.dyno.climb.gym.domain.command;

public record SearchQuery(String keyword, double lat, double lng) {

  public static SearchQuery of(String keyword, Double lat, Double lng) {
    return new SearchQuery(
        keyword, lat == null ? 37.5577908 : lat, lng == null ? 126.9256427 : lng);
  }
}

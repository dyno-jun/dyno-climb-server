package com.dyno.climb.gym.adapter.out.persistence.gym.db.entity;

import com.dyno.climb.gym.domain.vo.Location;
import jakarta.persistence.Embeddable;

@Embeddable
public class LocationDto {
  private double latitude;
  private double longitude;

  public LocationDto() {}

  public Location asDomain() {
    return new Location(latitude, longitude);
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}

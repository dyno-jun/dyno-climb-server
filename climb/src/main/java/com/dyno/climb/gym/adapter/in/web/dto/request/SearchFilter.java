package com.dyno.climb.gym.adapter.in.web.dto.request;

public class SearchFilter implements QueryParams {
  private String keyword;
  private Double lat;
  private Double lng;

  public SearchFilter() {}

  public SearchFilter(String keyword, Double lat, Double lng) {
    this.keyword = keyword;
    this.lat = lat;
    this.lng = lng;
  }

  public String getKeyword() {
    return keyword == null ? "" : keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  @Override
  public String getQueryString() {
    return "?keyword=" + getKeyword() + "&lat=" + getLat() + "&lng=" + getLng();
  }
}

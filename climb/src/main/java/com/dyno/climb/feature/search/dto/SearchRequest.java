package com.dyno.climb.feature.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchRequest {
  private String keyword;
  private Double lat;
  private Double lng;
}

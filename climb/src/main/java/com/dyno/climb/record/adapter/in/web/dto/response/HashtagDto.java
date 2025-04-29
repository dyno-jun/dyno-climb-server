package com.dyno.climb.record.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HashtagDto {
  private String id;
  private String name;

  public static HashtagDto create(String name) {
    return new HashtagDto(name, name);
  }

  public static HashtagDto create() {
    return new HashtagDto("id", "name");
  }
}

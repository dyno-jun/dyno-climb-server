package com.dyno.climb.gym.domain;

import com.dyno.climb.gym.domain.vo.*;
import com.dyno.climb.shared.vo.Image;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Gym {
  private final String id;
  private String name;
  private Address address;
  private Location location;
  private Tel tel;
  private Image image;
  private Link link;
  private LocalDateTime updatedAt;
  private List<BoulderLevel> levels;
}

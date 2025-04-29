package com.dyno.climb.mission.domain;

import com.dyno.climb.mission.domain.vo.MissionCondition;
import com.dyno.climb.mission.domain.vo.MissionType;
import com.dyno.climb.shared.vo.Image;
import jakarta.annotation.Nullable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mission {
  private final String id;
  @Nullable private String boulderProblemId;
  private String title;
  private String description;
  private List<MissionCondition> conditions;
  private Image image;
  private MissionType type;
  private int exp;
}

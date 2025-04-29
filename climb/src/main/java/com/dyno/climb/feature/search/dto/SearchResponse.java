package com.dyno.climb.feature.search.dto;

import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.mission.adapter.in.web.dto.MissionGroupParticipationDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResponse {
  private List<GymDto> gyms;
  private List<MissionGroupParticipationDto> missionGroups;
}

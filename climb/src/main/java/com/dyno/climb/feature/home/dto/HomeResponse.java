package com.dyno.climb.feature.home.dto;

import com.dyno.climb.feature.home.service.HomeDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.mission.adapter.in.web.dto.MissionGroupParticipationDto;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeResponse {
  private List<GymMissionGroupsDto> gyms;

  public HomeResponse() {}

  private HomeResponse(List<GymMissionGroupsDto> gyms) {
    this.gyms = gyms;
  }

  public static HomeResponse of(HomeDto homeDto) {
    Map<Gym, List<GymMissionGroupDto>> mapByGym =
        homeDto.results().stream().collect(Collectors.groupingBy(GymMissionGroupDto::gym));

    return new HomeResponse(
        mapByGym.keySet().stream()
            .map(
                gym ->
                    new GymMissionGroupsDto(
                        GymDto.of(gym),
                        mapByGym.get(gym).stream()
                            .map(MissionGroupParticipationDto::of)
                            .collect(Collectors.toList())))
            .collect(Collectors.toList()));
  }

  public List<GymMissionGroupsDto> getGyms() {
    return gyms;
  }
}

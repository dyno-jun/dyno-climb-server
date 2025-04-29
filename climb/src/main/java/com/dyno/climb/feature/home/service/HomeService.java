package com.dyno.climb.feature.home.service;

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.mission.application.service.GetMissionGroupUseCase;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.domain.command.MissionGroupSearchQuery;
import com.dyno.climb.user.domain.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HomeService implements GetHomeUseCase {
  private final GetMissionGroupUseCase getMissionGroupUseCase;

  public HomeService(GetMissionGroupUseCase getMissionGroupUseCase) {
    this.getMissionGroupUseCase = getMissionGroupUseCase;
  }

  @Override
  public HomeDto get(User user, SearchFilter searchFilter) {
    List<GymMissionGroupDto> results =
        getMissionGroupUseCase.getByQuery(
            user,
            new MissionGroupSearchQuery(
                searchFilter.getKeyword(), searchFilter.getLat(), searchFilter.getLng(), ""));
    return new HomeDto(results);
  }
}

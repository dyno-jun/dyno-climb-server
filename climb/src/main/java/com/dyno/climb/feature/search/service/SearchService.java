package com.dyno.climb.feature.search.service;

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.gym.application.port.in.GetGymUseCase;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;
import com.dyno.climb.mission.application.service.GetMissionGroupUseCase;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.domain.command.MissionGroupSearchQuery;
import com.dyno.climb.user.domain.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements SearchUseCase {
  private final GetGymUseCase getGymUseCase;
  private final GetMissionGroupUseCase getMissionGroupUseCase;

  public SearchService(GetGymUseCase getGymUseCase, GetMissionGroupUseCase getMissionGroupUseCase) {
    this.getGymUseCase = getGymUseCase;
    this.getMissionGroupUseCase = getMissionGroupUseCase;
  }

  @Override
  public SearchDto search(User user, SearchFilter searchFilter) {
    List<Gym> gyms =
        getGymUseCase.getByQuery(
            new SearchQuery(
                searchFilter.getKeyword(), searchFilter.getLat(), searchFilter.getLng()));
    List<GymMissionGroupDto> missionGroups =
        getMissionGroupUseCase.getByQuery(
            user,
            new MissionGroupSearchQuery(
                searchFilter.getKeyword(), searchFilter.getLat(), searchFilter.getLng(), ""));

    return new SearchDto(gyms, missionGroups);
  }
}

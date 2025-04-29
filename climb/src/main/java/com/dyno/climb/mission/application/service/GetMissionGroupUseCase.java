package com.dyno.climb.mission.application.service;

import com.dyno.climb.mission.domain.command.MissionGroupSearchQuery;
import com.dyno.climb.shared.error.BadRequestError;
import com.dyno.climb.user.domain.User;
import java.util.List;

public interface GetMissionGroupUseCase {

  List<GymMissionGroupDto> getByQuery(User user, MissionGroupSearchQuery query);

  List<GymMissionGroupDto> getByIds(User user, List<String> ids) throws BadRequestError;

  GymMissionGroupDto getById(User user, String id) throws BadRequestError;

  GymMissionGroupDto getMission(User user, String id, String missionId) throws BadRequestError;
}

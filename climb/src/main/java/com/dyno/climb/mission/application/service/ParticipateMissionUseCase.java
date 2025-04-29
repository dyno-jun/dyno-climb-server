package com.dyno.climb.mission.application.service;

import com.dyno.climb.shared.error.BadRequestError;
import com.dyno.climb.user.domain.User;

public interface ParticipateMissionUseCase {
  void participate(User user, String id, String missionId) throws BadRequestError;
}

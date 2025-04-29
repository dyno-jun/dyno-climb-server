package com.dyno.climb.record.application.service;

import com.dyno.climb.user.domain.User;

public interface SubmitMissionRecordUseCase {
  void submit(User user, String id, String missionGroupId, String missionId);
}

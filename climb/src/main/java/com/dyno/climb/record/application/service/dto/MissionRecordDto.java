package com.dyno.climb.record.application.service.dto;

import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.record.domain.ClimbingRecord;
import com.dyno.climb.user.domain.User;
import jakarta.annotation.Nullable;

public record MissionRecordDto(
    GymMissionGroupDto gymMissionGroupDto, GymClimbingRecordDto gymClimbingRecordDto) {
  public static MissionRecordDto create(
      User user,
      @Nullable GymMissionGroupDto gymMissionGroupDto,
      ClimbingRecord record,
      Gym gym,
      BoulderProblem boulderProblem) {

    return new MissionRecordDto(
        gymMissionGroupDto, new GymClimbingRecordDto(user, gym, boulderProblem, record));
  }
}

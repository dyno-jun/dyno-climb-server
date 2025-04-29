package com.dyno.climb.record.application.service.dto;

import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.record.domain.ClimbingRecord;
import com.dyno.climb.user.domain.User;

public record GymClimbingRecordDto(
    User user, Gym gym, BoulderProblem boulderProblem, ClimbingRecord climbingRecord) {}

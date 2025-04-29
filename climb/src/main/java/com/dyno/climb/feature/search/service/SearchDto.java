package com.dyno.climb.feature.search.service;

import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import java.util.List;

public record SearchDto(List<Gym> gyms, List<GymMissionGroupDto> missionGroups) {}

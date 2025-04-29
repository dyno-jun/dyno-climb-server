package com.dyno.climb.feature.home.service;

import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import java.util.List;

public record HomeDto(List<GymMissionGroupDto> results) {}

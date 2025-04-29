package com.dyno.climb.mission.domain.command;

public record MissionSearchQuery(
    String missionGroupId, String keyword, Double lat, Double lng, String gymId) {}

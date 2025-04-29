package com.dyno.climb.mission.domain.command;

public record MissionGroupSearchQuery(String keyword, Double lat, Double lng, String gymId) {}

package com.dyno.climb.mission.domain.vo;

public record MissionCondition(ConditionType name, InEquality inequality, int value) {}

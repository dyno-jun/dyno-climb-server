package com.dyno.climb.record.domain.command;

import java.time.LocalDateTime;
import java.util.List;

public record CreateRecordDto(
    String gymId,
    LocalDateTime date,
    String boulderProblemId,
    int tryCnt,
    boolean isClimbCompleted,
    List<String> videos,
    List<String> images,
    int duration,
    String memo,
    List<String> hashtagIds,
    boolean isPublic) {}

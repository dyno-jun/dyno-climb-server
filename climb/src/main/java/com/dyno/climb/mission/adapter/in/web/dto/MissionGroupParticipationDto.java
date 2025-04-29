package com.dyno.climb.mission.adapter.in.web.dto;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.domain.vo.MissionStatus;
import com.dyno.climb.mission.domain.vo.UserMission;
import com.dyno.climb.mission.domain.vo.UserMissionSummary;
import com.dyno.climb.mission.domain.vo.UserRecordSummary;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public record MissionGroupParticipationDto(
    MissionGroupDto missionGroup,
    @Nullable UserMissionGroupDto userMissionGroup,
    List<MissionParticipationDto> missions) {
  public MissionGroupParticipationDto(
      MissionGroupDto missionGroup,
      UserMissionGroupDto userMissionGroup,
      List<MissionParticipationDto> missions) {
    this.missionGroup = missionGroup;
    this.userMissionGroup = userMissionGroup;
    this.missions = missions;
  }

  public static MissionGroupParticipationDto of(GymMissionGroupDto gymMissionGroupDto) {
    Map<String, BoulderProblem> boulderProblemMap =
        gymMissionGroupDto.boulderProblems().stream()
            .collect(Collectors.toMap(BoulderProblem::getId, Function.identity()));
    Map<String, UserMission> userMissionMap =
        gymMissionGroupDto.missionGroup().getUserMissions().stream()
            .collect(Collectors.toMap(UserMission::getMissionId, Function.identity()));

    return new MissionGroupParticipationDto(
        MissionGroupDto.of(gymMissionGroupDto),
        UserMissionGroupDto.of(gymMissionGroupDto.missionGroup().getUserMissionGroup()),
        gymMissionGroupDto.missionGroup().getMissionGroup().getMissions().stream()
            .map(
                mission -> {
                  BoulderProblem boulderProblem =
                      boulderProblemMap.get(mission.getBoulderProblemId());
                  UserMission userMission = userMissionMap.get(mission.getId());
                  return MissionParticipationDto.of(mission, boulderProblem, userMission);
                })
            .collect(Collectors.toList()));
  }

  public static MissionGroupParticipationDto create(String id) {

    MissionGroupDto missionGroupDto =
        new MissionGroupDto(
            id,
            GymDto.create("gymdId"),
            "title",
            "description",
            "image",
            "time",
            new MissionGroupPeriodDto(LocalDateTime.now(), LocalDateTime.now()),
            100);

    UserMissionSummaryDto missionSummary =
        UserMissionSummaryDto.of(new UserMissionSummary(1, 1, true));
    UserMissionGroupDto userMissionGroupDto = new UserMissionGroupDto(missionSummary);

    MissionDto mission =
        new MissionDto("", BoulderProblemDto.create(), "title", "description", "", "", 20);
    UserRecordSummaryDto summary =
        UserRecordSummaryDto.of(new UserRecordSummary(3, MissionStatus.SUCCESS));
    UserMissionDto userMissionDto = new UserMissionDto(summary);

    List<MissionParticipationDto> missions =
        List.of(new MissionParticipationDto(mission, userMissionDto));

    return new MissionGroupParticipationDto(missionGroupDto, userMissionGroupDto, missions);
  }

  @Override
  public UserMissionGroupDto userMissionGroup() {
    return userMissionGroup;
  }
}

package com.dyno.climb.mission.adapter.in.web;

import com.dyno.climb.mission.adapter.in.web.dto.*;
import com.dyno.climb.mission.adapter.in.web.dto.request.MissionGroupSearchFilter;
import com.dyno.climb.mission.application.service.GetMissionGroupUseCase;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.application.service.ParticipateMissionUseCase;
import com.dyno.climb.mission.domain.command.MissionGroupSearchQuery;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission-groups")
public class MissionGroupController {
  private final GetMissionGroupUseCase getMissionGroupUseCase;
  private final ParticipateMissionUseCase participateMissionUseCase;

  public MissionGroupController(
      GetMissionGroupUseCase getMissionGroupUseCase,
      ParticipateMissionUseCase participateMissionUseCase) {
    this.getMissionGroupUseCase = getMissionGroupUseCase;
    this.participateMissionUseCase = participateMissionUseCase;
  }

  @GetMapping()
  public ResponseEntity<MissionGroupResponse> getMissionGroups(
      @AuthenticationPrincipal User user, @Valid @ModelAttribute MissionGroupSearchFilter filter) {
    List<GymMissionGroupDto> results =
        getMissionGroupUseCase.getByQuery(
            user,
            new MissionGroupSearchQuery(
                filter.getKeyword(), filter.getLat(), filter.getLng(), filter.getGymId()));

    return ResponseEntity.ok(
        new MissionGroupResponse(
            results.stream().map(MissionGroupParticipationDto::of).collect(Collectors.toList())));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MissionGroupParticipationDto> getMissionGroupById(
      @AuthenticationPrincipal User user, @PathVariable("id") String id) {
    GymMissionGroupDto result = getMissionGroupUseCase.getById(user, id);

    return ResponseEntity.ok(MissionGroupParticipationDto.of(result));
  }

  @PatchMapping("/{id}/missions/{missionId}/participation")
  public ResponseEntity<?> participateMission(
      @AuthenticationPrincipal User user,
      @PathVariable("id") String id,
      @PathVariable("missionId") String missionId) {
    participateMissionUseCase.participate(user, id, missionId);

    return ResponseEntity.ok(null);
  }

  @GetMapping("/{id}/missions/{missionId}")
  public ResponseEntity<MissionGroupWithMissionDto> getMission(
      @AuthenticationPrincipal User user,
      @PathVariable("id") String id,
      @PathVariable("missionId") String missionId) {
    GymMissionGroupDto result = getMissionGroupUseCase.getMission(user, id, missionId);
    MissionGroupParticipationDto missionGroupParticipationDto =
        MissionGroupParticipationDto.of(result);

    return ResponseEntity.ok(
        new MissionGroupWithMissionDto(
            missionGroupParticipationDto.missionGroup(),
            missionGroupParticipationDto.userMissionGroup(),
            missionGroupParticipationDto.missions().stream().findFirst().get()));
  }
}

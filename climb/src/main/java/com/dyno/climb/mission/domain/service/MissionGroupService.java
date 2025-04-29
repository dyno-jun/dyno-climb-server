package com.dyno.climb.mission.domain.service;

import static java.util.stream.Collectors.groupingBy;

import com.dyno.climb.gym.application.port.in.GetBoulderProblemUseCase;
import com.dyno.climb.gym.application.port.in.GetGymUseCase;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;
import com.dyno.climb.mission.application.port.MissionGroupPort;
import com.dyno.climb.mission.application.port.UserMissionPort;
import com.dyno.climb.mission.application.service.GetMissionGroupUseCase;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.application.service.ParticipateMissionUseCase;
import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.MissionGroup;
import com.dyno.climb.mission.domain.MissionGroupParticipation;
import com.dyno.climb.mission.domain.command.MissionGroupSearchQuery;
import com.dyno.climb.mission.domain.vo.UserMission;
import com.dyno.climb.mission.domain.vo.UserMissionGroup;
import com.dyno.climb.shared.error.BadRequestError;
import com.dyno.climb.user.domain.User;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MissionGroupService implements GetMissionGroupUseCase, ParticipateMissionUseCase {
  private final MissionGroupPort missionGroupPort;
  private final UserMissionPort userMissionPort;
  private final GetGymUseCase getGymUseCase;
  private final GetBoulderProblemUseCase getBoulderProblemUseCase;

  public MissionGroupService(
      MissionGroupPort missionGroupPort,
      UserMissionPort userMissionPort,
      GetGymUseCase getGymUseCase,
      GetBoulderProblemUseCase getBoulderProblemUseCase) {
    this.missionGroupPort = missionGroupPort;
    this.userMissionPort = userMissionPort;
    this.getGymUseCase = getGymUseCase;
    this.getBoulderProblemUseCase = getBoulderProblemUseCase;
  }

  private static GymMissionGroupDto getGymMissionGroupDto(
      Map<String, List<UserMission>> userMissionMap,
      Map<String, Gym> gyms,
      Map<String, BoulderProblem> problems,
      MissionGroup missionGroup) {
    List<UserMission> missions = userMissionMap.get(missionGroup.getId());

    return GymMissionGroupDto.of(
        gyms,
        problems,
        new MissionGroupParticipation(
            missionGroup, UserMissionGroup.of(missionGroup, missions), missions));
  }

  @Override
  public List<GymMissionGroupDto> getByQuery(User user, MissionGroupSearchQuery query) {
    List<Gym> gyms =
        getGymUseCase.getByQuery(new SearchQuery(query.keyword(), query.lat(), query.lng()));
    if (!query.gymId().isEmpty()) {
      gyms =
          gyms.stream()
              .filter(gym -> gym.getId().equals(query.gymId()))
              .collect(Collectors.toList());
    }

    List<MissionGroup> missionGroups =
        missionGroupPort.findByGymIds(gyms.stream().map(Gym::getId).collect(Collectors.toList()));
    if (!query.keyword().isEmpty()) {
      missionGroups =
          missionGroups.stream()
              .filter(
                  missionGroup ->
                      missionGroup.getTitle().contains(query.keyword())
                          || missionGroup.getDescription().contains(query.keyword()))
              .collect(Collectors.toList());
    }
    return getGymMissionGroups(missionGroups, gyms, user);
  }

  @Override
  public List<GymMissionGroupDto> getByIds(User user, List<String> ids) throws BadRequestError {
    List<MissionGroup> missionGroups = missionGroupPort.findByIds(ids);
    List<Gym> gyms =
        getGymUseCase.getByIds(
            missionGroups.stream().map(MissionGroup::getGymId).collect(Collectors.toList()));

    return getGymMissionGroups(missionGroups, gyms, user);
  }

  private List<GymMissionGroupDto> getGymMissionGroups(
      List<MissionGroup> missionGroups, List<Gym> gyms, User user) {
    final Map<String, List<UserMission>> userMissionMap = getUserMissionMap(user);
    final Map<String, BoulderProblem> problems = getStringBoulderProblemMap(missionGroups);

    return missionGroups.stream()
        .map(
            missionGroup ->
                getGymMissionGroupDto(
                    userMissionMap,
                    gyms.stream().collect(Collectors.toMap(Gym::getId, gym -> gym)),
                    problems,
                    missionGroup))
        .collect(Collectors.toList());
  }

  private Map<String, BoulderProblem> getStringBoulderProblemMap(List<MissionGroup> missionGroups) {
    List<String> boulderProblemIds = new ArrayList<>();
    missionGroups.forEach(
        missionGroup ->
            missionGroup
                .getMissions()
                .forEach(mission -> boulderProblemIds.add(mission.getBoulderProblemId())));

    return getBoulderProblemUseCase.getByIds(boulderProblemIds).stream()
        .collect(Collectors.toMap(BoulderProblem::getId, boulderProblem -> boulderProblem));
  }

  private Map<String, Gym> getGymMap(List<MissionGroup> missionGroups) {
    List<String> gymIds = missionGroups.stream().map(MissionGroup::getGymId).distinct().toList();
    return getGymUseCase.getByIds(gymIds).stream()
        .collect(Collectors.toMap(Gym::getId, gym -> gym));
  }

  private Map<String, List<UserMission>> getUserMissionMap(User user) {
    if (user == null) return new HashMap<>();

    return userMissionPort.findByUserId(user.getId()).stream()
        .collect(groupingBy((UserMission::getMissionGroupId)));
  }

  @Override
  public GymMissionGroupDto getById(User user, String id) throws BadRequestError {
    MissionGroup missionGroup = getMissionGroup(id);
    List<UserMission> userMissions =
        userMissionPort.findByUserIdMissionGroupId(user.getId(), missionGroup.getId());
    List<MissionGroup> missionGroups = List.of(missionGroup);
    Map<String, Gym> gymMap = getGymMap(missionGroups);
    Map<String, BoulderProblem> boulderProblemMap = getStringBoulderProblemMap(missionGroups);

    return GymMissionGroupDto.of(
        gymMap,
        boulderProblemMap,
        new MissionGroupParticipation(
            missionGroup, UserMissionGroup.of(missionGroup, userMissions), userMissions));
  }

  private MissionGroup getMissionGroup(String id) throws BadRequestError {
    MissionGroup missionGroup = missionGroupPort.findById(id);
    if (missionGroup == null) {
      throw new BadRequestError("미션 그룹이 존재하지 않습니다");
    }

    return missionGroup;
  }

  @Override
  public GymMissionGroupDto getMission(User user, String id, String missionId)
      throws BadRequestError {
    GymMissionGroupDto result = getById(user, id);
    result.missionGroup().filterByMissionId(missionId);

    return result;
  }

  @Override
  public void participate(User user, String id, String missionId) throws BadRequestError {
    MissionGroup missionGroup = getMissionGroup(id);
    Mission mission = missionGroup.getMission(missionId);
    UserMission userMission = UserMission.of(user, missionGroup, mission);

    userMissionPort.save(userMission);
  }
}

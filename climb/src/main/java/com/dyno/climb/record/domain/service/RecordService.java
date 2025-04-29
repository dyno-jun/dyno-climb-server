package com.dyno.climb.record.domain.service;

import com.dyno.climb.gym.application.port.in.GetBoulderProblemUseCase;
import com.dyno.climb.gym.application.port.in.GetGymUseCase;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.mission.application.service.GetMissionGroupUseCase;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.record.application.port.ClimbingRecordPort;
import com.dyno.climb.record.application.service.*;
import com.dyno.climb.record.application.service.dto.MissionRecordDto;
import com.dyno.climb.record.domain.ClimbingRecord;
import com.dyno.climb.record.domain.MissionClimbingRecord;
import com.dyno.climb.record.domain.command.CreateRecordDto;
import com.dyno.climb.record.domain.command.RecordSearchQuery;
import com.dyno.climb.shared.error.BadRequestError;
import com.dyno.climb.user.application.port.out.UserPort;
import com.dyno.climb.user.domain.User;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService
    implements GetRecordUseCase,
        SubmitMissionRecordUseCase,
        CreateRecordUseCase,
        UpdateRecordUseCase,
        DeleteRecordUseCase {
  private final UserPort userPort;
  private final GetGymUseCase getGymUseCase;
  private final GetBoulderProblemUseCase getBoulderProblemUseCase;
  private final ClimbingRecordPort recordPort;
  private final GetMissionGroupUseCase getMissionGroupUseCase;

  @Autowired
  public RecordService(
      UserPort userPort,
      GetGymUseCase getGymUseCase,
      GetBoulderProblemUseCase getBoulderProblemUseCase,
      ClimbingRecordPort recordPort,
      GetMissionGroupUseCase getMissionGroupUseCase,
      FileUseCase fileUseCase) {
    this.userPort = userPort;
    this.getGymUseCase = getGymUseCase;
    this.getBoulderProblemUseCase = getBoulderProblemUseCase;
    this.recordPort = recordPort;
    this.getMissionGroupUseCase = getMissionGroupUseCase;
  }

  @Override
  public String save(User user, CreateRecordDto command) {
    ClimbingRecord climbingRecord = ClimbingRecord.create(user.getId(), command);
    recordPort.save(climbingRecord);

    return climbingRecord.getId();
  }

  @Override
  public void delete(User user, String id) {
    ClimbingRecord record = getRecord(user, id);
    record.delete();
    recordPort.save(record);
  }

  @Override
  public List<MissionRecordDto> getRecordsByQuery(RecordSearchQuery query) {
    List<ClimbingRecord> records = recordPort.findByQuery(query);
    return getMissionRecords(null, records);
  }

  private List<MissionRecordDto> getMissionRecords(User user, List<ClimbingRecord> records) {
    Map<String, User> userMap = getUserMap(records);
    Map<String, GymMissionGroupDto> missionGroupMap = getGymMissionGroupMap(user, records);
    Map<String, Gym> gymMap =
        getGymUseCase
            .getByIds(records.stream().map(ClimbingRecord::getGymId).collect(Collectors.toList()))
            .stream()
            .collect(Collectors.toMap(Gym::getId, Function.identity()));

    Map<String, BoulderProblem> boulderProblemMap =
        getBoulderProblemUseCase
            .getByIds(
                records.stream()
                    .map(ClimbingRecord::getBoulderProblemId)
                    .collect(Collectors.toList()))
            .stream()
            .collect(Collectors.toMap(BoulderProblem::getId, Function.identity()));

    return records.stream()
        .map(
            record ->
                MissionRecordDto.create(
                    userMap.get(record.getUserId()),
                    missionGroupMap.get(record.getMissionGroupId()),
                    record,
                    gymMap.get(record.getGymId()),
                    boulderProblemMap.get(record.getBoulderProblemId())))
        .collect(Collectors.toList());
  }

  private Map<String, GymMissionGroupDto> getGymMissionGroupMap(
      User user, List<ClimbingRecord> records) {
    List<String> missionGroupIds =
        records.stream()
            .map(ClimbingRecord::getMissionGroupId)
            .filter(id -> !id.isEmpty())
            .toList();

    return getMissionGroupUseCase.getByIds(user, missionGroupIds).stream()
        .collect(Collectors.toMap(GymMissionGroupDto::getId, Function.identity()));
  }

  private Map<String, User> getUserMap(List<ClimbingRecord> records) {
    List<String> userIds = records.stream().map(ClimbingRecord::getUserId).toList();
    return userPort.findByIds(userIds).stream()
        .collect(Collectors.toMap(User::getId, Function.identity()));
  }

  @Override
  public List<MissionRecordDto> getRecords(User user) {
    List<ClimbingRecord> records = recordPort.findByUserId(user.getId());

    return getMissionRecords(user, records);
  }

  @Override
  public MissionRecordDto getById(User user, String id) {
    ClimbingRecord record = getRecord(user, id);
    Gym gym = getGymUseCase.getById(record.getGymId());
    BoulderProblem boulderProblem =
        record.getBoulderProblemId() == null
            ? null
            : getBoulderProblemUseCase.getById(record.getBoulderProblemId());

    GymMissionGroupDto gymMissionGroupDto =
        record.getMissionClimbingRecord() == null
            ? null
            : getMissionGroupUseCase.getMission(
                user,
                record.getMissionClimbingRecord().getMissionGroupId(),
                record.getMissionClimbingRecord().getMissionId());

    return MissionRecordDto.create(user, gymMissionGroupDto, record, gym, boulderProblem);
  }

  @Override
  public void submit(User user, String id, String missionGroupId, String missionId) {
    ClimbingRecord record = getRecord(user, id);
    if (record.isMissionRecord()) {
      throw new BadRequestError("already submitted record");
    }
    GymMissionGroupDto gymMissionGroupDto = getMissionGroupUseCase.getById(user, missionGroupId);

    MissionClimbingRecord missionClimbingRecord =
        MissionClimbingRecord.create(
            gymMissionGroupDto.missionGroup().getMissionGroup(),
            gymMissionGroupDto.missionGroup().getMissionGroup().getMission(missionId));

    record.submit(missionClimbingRecord);
    recordPort.save(record);
  }

  @Override
  public void update(User user, String id, CreateRecordDto dto) {
    ClimbingRecord record = getRecord(user, id);
    record.update(dto);
    recordPort.save(record);
  }

  private ClimbingRecord getRecord(User user, String id) {
    ClimbingRecord record = recordPort.findByUserIdAndId(user.getId(), id);
    if (record == null) {
      throw new BadRequestError("record not exists by " + id);
    }

    return record;
  }
}

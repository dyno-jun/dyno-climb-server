package com.dyno.climb.record.adapter.in.web.mapper;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.mission.adapter.in.web.dto.MissionParticipationDto;
import com.dyno.climb.mission.domain.Mission;
import com.dyno.climb.mission.domain.vo.UserMission;
import com.dyno.climb.record.adapter.in.web.dto.request.CreateRecordRequest;
import com.dyno.climb.record.adapter.in.web.dto.response.HashtagDto;
import com.dyno.climb.record.adapter.in.web.dto.response.RecordDto;
import com.dyno.climb.record.adapter.in.web.dto.response.UserMissionRecordDto;
import com.dyno.climb.record.application.service.FileUseCase;
import com.dyno.climb.record.application.service.dto.GymClimbingRecordDto;
import com.dyno.climb.record.application.service.dto.MissionRecordDto;
import com.dyno.climb.record.domain.command.CreateRecordDto;
import com.dyno.climb.shared.dto.ImageDto;
import com.dyno.climb.shared.dto.VideoDto;
import com.dyno.climb.user.adapter.in.web.dto.response.UserDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {
  private final FileUseCase fileUseCase;

  public RecordMapper(FileUseCase fileUseCase) {
    this.fileUseCase = fileUseCase;
  }

  public CreateRecordDto mapTo(CreateRecordRequest request) {
    return new CreateRecordDto(
        request.getGymId(),
        request.getDate(),
        request.getBoulderProblemId(),
        request.getTryCnt(),
        request.isClimbCompleted(),
        request.getVideos().stream().map(VideoDto::getUrl).collect(Collectors.toList()),
        request.getImages().stream().map(ImageDto::getUrl).collect(Collectors.toList()),
        request.getDuration(),
        request.getMemo(),
        request.getHashtagIds(),
        request.isPublic());
  }

  public UserMissionRecordDto of(MissionRecordDto missionRecordDto) {
    MissionParticipationDto missionParticipationDto = null;
    if (missionRecordDto.gymMissionGroupDto() != null) {
      Mission m =
          missionRecordDto
              .gymMissionGroupDto()
              .missionGroup()
              .getMissionGroup()
              .getMission(missionRecordDto.gymClimbingRecordDto().climbingRecord().getMissionId());
      UserMission userMission =
          missionRecordDto.gymMissionGroupDto().missionGroup().findByMissionId(m.getId());

      missionParticipationDto =
          MissionParticipationDto.of(
              m, missionRecordDto.gymClimbingRecordDto().boulderProblem(), userMission);
    }

    return new UserMissionRecordDto(
        missionParticipationDto, mapTo(missionRecordDto.gymClimbingRecordDto()));
  }

  public RecordDto mapTo(GymClimbingRecordDto climbingRecord) {
    return new RecordDto(
        climbingRecord.climbingRecord().getId(),
        UserDto.create(climbingRecord.user()),
        GymDto.of(climbingRecord.gym()),
        climbingRecord.climbingRecord().getDate(),
        BoulderProblemDto.of(climbingRecord.boulderProblem()),
        climbingRecord.climbingRecord().getTryCnt(),
        climbingRecord.climbingRecord().isCompleted(),
        climbingRecord.climbingRecord().getVideos().stream()
            .map(video -> new VideoDto(fileUseCase.generatePreSignedUrl(video.url())))
            .collect(Collectors.toList()),
        climbingRecord.climbingRecord().getImages().stream()
            .map(image -> new ImageDto(fileUseCase.generatePreSignedUrl(image.url())))
            .collect(Collectors.toList()),
        climbingRecord.climbingRecord().getDuration(),
        climbingRecord.climbingRecord().getMemo(),
        climbingRecord.climbingRecord().getHashtags().stream()
            .map(HashtagDto::create)
            .collect(Collectors.toList()),
        climbingRecord.climbingRecord().isPublic());
  }
}

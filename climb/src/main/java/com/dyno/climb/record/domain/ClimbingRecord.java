package com.dyno.climb.record.domain;

import com.dyno.climb.record.domain.command.CreateRecordDto;
import com.dyno.climb.shared.utils.IDGenerator;
import com.dyno.climb.shared.vo.Image;
import com.dyno.climb.shared.vo.Video;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClimbingRecord {
  private final String id;
  private final String userId;
  private String gymId;
  private LocalDateTime date;
  private String boulderProblemId;
  private int tryCnt;
  private boolean completed;
  private List<Video> videos;
  private List<Image> images;
  private int duration;
  private List<String> hashtags;
  private String memo;
  private boolean isPublic;
  private boolean isDeleted;

  @Nullable private MissionClimbingRecord missionClimbingRecord;

  public static ClimbingRecord create(String userId, CreateRecordDto createRecordDto) {
    return new ClimbingRecord(
        IDGenerator.generate(),
        userId,
        createRecordDto.gymId(),
        createRecordDto.date(),
        createRecordDto.boulderProblemId(),
        createRecordDto.tryCnt(),
        createRecordDto.isClimbCompleted(),
        createRecordDto.videos().stream().map(Video::new).collect(Collectors.toList()),
        createRecordDto.images().stream().map(Image::new).collect(Collectors.toList()),
        createRecordDto.duration(),
        createRecordDto.hashtagIds(),
        createRecordDto.memo(),
        createRecordDto.isPublic(),
        false,
        null);
  }

  public boolean isMissionRecord() {
    return this.missionClimbingRecord != null;
  }

  public void submit(MissionClimbingRecord missionClimbingRecord) {
    this.missionClimbingRecord = missionClimbingRecord;
  }

  public String getMissionId() {
    return missionClimbingRecord == null ? "" : missionClimbingRecord.getMissionId();
  }

  public String getMissionGroupId() {
    return missionClimbingRecord == null ? "" : missionClimbingRecord.getMissionGroupId();
  }

  public void delete() {
    this.isDeleted = true;
  }

  public void update(CreateRecordDto dto) {
    this.gymId = dto.gymId();
    this.date = dto.date();
    this.boulderProblemId = dto.boulderProblemId();
    this.tryCnt = dto.tryCnt();
    this.completed = dto.isClimbCompleted();
    this.videos = dto.videos().stream().map(Video::new).collect(Collectors.toList());
    this.images = dto.images().stream().map(Image::new).collect(Collectors.toList());
    this.duration = dto.duration();
    this.memo = dto.memo();
    this.hashtags = dto.hashtagIds();
    this.isPublic = dto.isPublic();
  }
}

package com.dyno.climb.record.adapter.out.persistence;

import com.dyno.climb.record.adapter.out.persistence.db.HashTagRepository;
import com.dyno.climb.record.adapter.out.persistence.db.MissionClimbingRecordRepository;
import com.dyno.climb.record.adapter.out.persistence.db.entity.*;
import com.dyno.climb.record.domain.ClimbingRecord;
import com.dyno.climb.record.domain.MissionClimbingRecord;
import com.dyno.climb.shared.utils.IDGenerator;
import com.dyno.climb.shared.vo.Image;
import com.dyno.climb.shared.vo.Video;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ClimbingRecordMapper {

  private final MissionClimbingRecordRepository missionClimbingRecordRepository;
  private final HashTagRepository hashTagRepository;

  public ClimbingRecordMapper(
      MissionClimbingRecordRepository missionClimbingRecordRepository,
      HashTagRepository hashTagRepository) {
    this.missionClimbingRecordRepository = missionClimbingRecordRepository;
    this.hashTagRepository = hashTagRepository;
  }

  public ClimbingRecord mapTo(ClimbingRecordEntity climbingRecordEntity) {
    if (climbingRecordEntity == null) {
      return null;
    }
    return new ClimbingRecord(
        climbingRecordEntity.getUid(),
        climbingRecordEntity.getUserId(),
        climbingRecordEntity.getGymId(),
        climbingRecordEntity.getDate(),
        climbingRecordEntity.getBoulderProblemId(),
        climbingRecordEntity.getTryCnt(),
        climbingRecordEntity.isCompleted(),
        mapToVideo(climbingRecordEntity.getVideos()),
        mapToImage(climbingRecordEntity.getImages()),
        climbingRecordEntity.getDuration(),
        mapToHashTag(climbingRecordEntity.getHashTags()),
        climbingRecordEntity.getMemo(),
        climbingRecordEntity.isPublic(),
        climbingRecordEntity.isDeleted(),
        mapTo(climbingRecordEntity.getMissionClimbingRecord()));
  }

  private MissionClimbingRecord mapTo(MissionClimbingRecordEntity entity) {
    if (entity == null) return null;

    return new MissionClimbingRecord(
        entity.getUid(), entity.getMissionGroupId(), entity.getMissionId(), entity.getStatus());
  }

  public ClimbingRecordEntity mapTo(long id, ClimbingRecord climbingRecord) {
    ClimbingRecordEntity entity =
        new ClimbingRecordEntity(
            id,
            climbingRecord.getId(),
            climbingRecord.getUserId(),
            climbingRecord.getGymId(),
            climbingRecord.getDate(),
            climbingRecord.getBoulderProblemId(),
            climbingRecord.getTryCnt(),
            climbingRecord.isCompleted(),
            climbingRecord.getDuration(),
            climbingRecord.getMemo(),
            climbingRecord.isPublic(),
            climbingRecord.isDeleted());
    if (climbingRecord.getMissionClimbingRecord() != null) {
      MissionClimbingRecordEntity missionClimbingRecord =
          getByUid(climbingRecord.getMissionClimbingRecord().getUid(), entity, climbingRecord);
      entity.setMissionClimbingRecord(missionClimbingRecord);
    }

    entity.getImages().clear();
    entity.setImages(
        climbingRecord.getImages().stream()
            .map(image -> new ImageEntity(0L, IDGenerator.generate(), entity, image.url()))
            .collect(Collectors.toList()));

    entity.getVideos().clear();
    entity.setVideos(
        climbingRecord.getVideos().stream()
            .map(video -> new VideoEntity(0L, IDGenerator.generate(), entity, video.url()))
            .collect(Collectors.toList()));

    entity.getHashTags().clear();
    entity.setHashTags(
        climbingRecord.getHashtags().stream()
            .map(
                tag ->
                    new HashTagClimbingRecordEntity(
                        0L, IDGenerator.generate(), entity, getByTag(tag)))
            .collect(Collectors.toList()));

    return entity;
  }

  private MissionClimbingRecordEntity getByUid(
      String uid, ClimbingRecordEntity entity, ClimbingRecord climbingRecord) {
    Optional<MissionClimbingRecordEntity> missionClimbingRecord =
        missionClimbingRecordRepository.findByUid(uid);
    return missionClimbingRecord.orElseGet(
        () ->
            new MissionClimbingRecordEntity(
                0L,
                climbingRecord.getId(),
                entity,
                climbingRecord.getMissionClimbingRecord().getMissionGroupId(),
                climbingRecord.getMissionClimbingRecord().getMissionId(),
                climbingRecord.getMissionClimbingRecord().getStatus()));
  }

  private HashTagEntity getByTag(String tag) {
    Optional<HashTagEntity> hashTag = hashTagRepository.findByName(tag);
    return hashTag.orElseGet(() -> new HashTagEntity(0L, IDGenerator.generate(), tag));
  }

  public List<ClimbingRecord> mapToClimbingRecords(List<ClimbingRecordEntity> climbingRecords) {
    return climbingRecords.stream().map(this::mapTo).collect(Collectors.toList());
  }

  public List<String> mapToHashTag(List<HashTagClimbingRecordEntity> hashTags) {
    if (hashTags == null) return new ArrayList<>();

    return hashTags.stream()
        .map(hashTagClimbingRecordEntity -> hashTagClimbingRecordEntity.getHashTag().getName())
        .collect(Collectors.toList());
  }

  public List<Image> mapToImage(List<ImageEntity> images) {
    if (images == null) return new ArrayList<>();

    return images.stream()
        .map(imageEntity -> new Image(imageEntity.getUrl()))
        .collect(Collectors.toList());
  }

  public List<Video> mapToVideo(List<VideoEntity> videos) {
    if (videos == null) return new ArrayList<>();

    return videos.stream()
        .map(videoEntity -> new Video(videoEntity.getUrl()))
        .collect(Collectors.toList());
  }
}

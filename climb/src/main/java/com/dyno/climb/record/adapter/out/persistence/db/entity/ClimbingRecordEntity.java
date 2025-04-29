package com.dyno.climb.record.adapter.out.persistence.db.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "climbing_records")
public class ClimbingRecordEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  private String userId;

  private String gymId;
  private LocalDateTime date;
  private String boulderProblemId;
  private int tryCnt;
  private boolean completed;
  private int duration;
  private String memo;

  private boolean isPublic;

  private boolean isDeleted;

  @OneToOne(
      mappedBy = "record",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  private MissionClimbingRecordEntity missionClimbingRecord;

  @OneToMany(
      mappedBy = "record",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  private List<HashTagClimbingRecordEntity> hashTags;

  @OneToMany(
      mappedBy = "record",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  private List<ImageEntity> images;

  @OneToMany(
      mappedBy = "record",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  private List<VideoEntity> videos;

  public ClimbingRecordEntity() {}

  public ClimbingRecordEntity(
      Long id,
      String uid,
      String userId,
      String gymId,
      LocalDateTime date,
      String boulderProblemId,
      int tryCnt,
      boolean completed,
      int duration,
      String memo,
      boolean isPublic,
      boolean isDeleted) {
    this.id = id;
    this.uid = uid;
    this.userId = userId;
    this.gymId = gymId;
    this.date = date;
    this.boulderProblemId = boulderProblemId;
    this.tryCnt = tryCnt;
    this.completed = completed;
    this.duration = duration;
    this.memo = memo;
    this.isPublic = isPublic;
    this.isDeleted = isDeleted;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getUserId() {
    return userId;
  }

  public String getGymId() {
    return gymId;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public String getBoulderProblemId() {
    return boulderProblemId;
  }

  public int getTryCnt() {
    return tryCnt;
  }

  public boolean isCompleted() {
    return completed;
  }

  public int getDuration() {
    return duration;
  }

  public String getMemo() {
    return memo;
  }

  public MissionClimbingRecordEntity getMissionClimbingRecord() {
    return missionClimbingRecord;
  }

  public void setMissionClimbingRecord(MissionClimbingRecordEntity missionClimbingRecord) {
    this.missionClimbingRecord = missionClimbingRecord;
  }

  public List<HashTagClimbingRecordEntity> getHashTags() {
    return hashTags == null ? new ArrayList<>() : hashTags;
  }

  public void setHashTags(List<HashTagClimbingRecordEntity> hashTags) {
    this.hashTags = hashTags;
  }

  public List<ImageEntity> getImages() {
    return images == null ? new ArrayList<>() : images;
  }

  public void setImages(List<ImageEntity> images) {
    this.images = images;
  }

  public List<VideoEntity> getVideos() {
    return videos == null ? new ArrayList<>() : videos;
  }

  public void setVideos(List<VideoEntity> videos) {
    this.videos = videos;
  }
}

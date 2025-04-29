package com.dyno.climb.record.adapter.in.web.dto.request;

import com.dyno.climb.shared.dto.ImageDto;
import com.dyno.climb.shared.dto.VideoDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class CreateRecordRequest {
  private String gymId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;

  private String boulderProblemId;
  private int tryCnt;

  @JsonProperty("isClimbCompleted")
  private boolean isClimbCompleted;

  private List<VideoDto> videos;
  private List<ImageDto> images;
  private int duration;
  private String memo;
  private List<String> hashtagIds;

  @JsonProperty("isPublic")
  private boolean isPublic;

  public CreateRecordRequest() {}

  public CreateRecordRequest(
      String gymId,
      LocalDateTime date,
      String boulderProblemId,
      int tryCnt,
      boolean isClimbCompleted,
      List<VideoDto> videos,
      List<ImageDto> images,
      int duration,
      String memo,
      List<String> hashtagIds,
      boolean isPublic) {
    this.gymId = gymId;
    this.date = date;
    this.boulderProblemId = boulderProblemId;
    this.tryCnt = tryCnt;
    this.isClimbCompleted = isClimbCompleted;
    this.videos = videos;
    this.images = images;
    this.duration = duration;
    this.memo = memo;
    this.hashtagIds = hashtagIds;
    this.isPublic = isPublic;
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

  @JsonProperty("isClimbCompleted")
  public boolean isClimbCompleted() {
    return isClimbCompleted;
  }

  public List<VideoDto> getVideos() {
    return videos;
  }

  public List<ImageDto> getImages() {
    return images;
  }

  public int getDuration() {
    return duration;
  }

  public String getMemo() {
    return memo;
  }

  public List<String> getHashtagIds() {
    return hashtagIds;
  }

  @JsonProperty("isPublic")
  public boolean isPublic() {
    return isPublic;
  }
}

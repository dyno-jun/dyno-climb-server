package com.dyno.climb.record.adapter.in.web.dto.response;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.shared.dto.ImageDto;
import com.dyno.climb.shared.dto.VideoDto;
import com.dyno.climb.user.adapter.in.web.dto.response.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class RecordDto {
  private final String id;
  private final UserDto user;
  private final GymDto gym;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime date;

  private final BoulderProblemDto boulderProblem;
  private final int tryCnt;
  private final boolean isClimbCompleted;
  private final List<VideoDto> videos;
  private final List<ImageDto> images;
  private final int duration;
  private final String memo;
  private final List<HashtagDto> hashtags;
  private final boolean isPublic;

  public RecordDto(
      String id,
      UserDto user,
      GymDto gym,
      LocalDateTime date,
      BoulderProblemDto boulderProblem,
      int tryCnt,
      boolean isClimbCompleted,
      List<VideoDto> videos,
      List<ImageDto> images,
      int duration,
      String memo,
      List<HashtagDto> hashtags,
      boolean isPublic) {
    this.id = id;
    this.user = user;
    this.gym = gym;
    this.date = date;
    this.boulderProblem = boulderProblem;
    this.tryCnt = tryCnt;
    this.isClimbCompleted = isClimbCompleted;
    this.videos = videos;
    this.images = images;
    this.duration = duration;
    this.memo = memo;
    this.hashtags = hashtags;
    this.isPublic = isPublic;
  }

  public static RecordDto create() {
    return new RecordDto(
        "recordId",
        UserDto.create("userId"),
        GymDto.create("gymId"),
        LocalDateTime.now(),
        BoulderProblemDto.create(),
        2,
        true,
        List.of(new VideoDto("videoUrl")),
        List.of(new ImageDto("imageUrl")),
        10,
        "memo",
        List.of(new HashtagDto("id", "dyno")),
        true);
  }

  public String getId() {
    return id;
  }

  public UserDto getUser() {
    return user;
  }

  public GymDto getGym() {
    return gym;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public BoulderProblemDto getBoulderProblem() {
    return boulderProblem;
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

  public List<HashtagDto> getHashtags() {
    return hashtags;
  }

  @JsonProperty("isPublic")
  public boolean isPublic() {
    return isPublic;
  }
}

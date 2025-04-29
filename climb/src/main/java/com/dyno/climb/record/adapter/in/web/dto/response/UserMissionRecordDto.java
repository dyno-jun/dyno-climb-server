package com.dyno.climb.record.adapter.in.web.dto.response;

import com.dyno.climb.mission.adapter.in.web.dto.MissionParticipationDto;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserMissionRecordDto {
  @Nullable private MissionParticipationDto mission;
  private RecordDto record;

  public static UserMissionRecordDto of() {
    return new UserMissionRecordDto(MissionParticipationDto.create(), RecordDto.create());
  }
}

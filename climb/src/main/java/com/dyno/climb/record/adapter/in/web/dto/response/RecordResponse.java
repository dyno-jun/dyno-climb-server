package com.dyno.climb.record.adapter.in.web.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecordResponse {
  private List<UserMissionRecordDto> records;
}

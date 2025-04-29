package com.dyno.climb.record.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PreSignedResponse {
  private String preSignedUrl;
  private String filePath;
}

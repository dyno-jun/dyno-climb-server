package com.dyno.climb.record.adapter.in.web.dto.response;

import java.util.List;

public class HashtagResponse {
  private final List<HashtagDto> hashtags;

  public HashtagResponse(List<HashtagDto> hashtags) {
    this.hashtags = hashtags;
  }

  public List<HashtagDto> getHashtags() {
    return hashtags;
  }
}

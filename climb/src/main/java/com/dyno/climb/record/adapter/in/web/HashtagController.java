package com.dyno.climb.record.adapter.in.web;

import com.dyno.climb.record.adapter.in.web.dto.request.*;
import com.dyno.climb.record.adapter.in.web.dto.response.HashtagResponse;
import com.dyno.climb.record.adapter.in.web.mapper.HashtagMapper;
import com.dyno.climb.record.application.service.CreateHashtagUseCase;
import com.dyno.climb.record.application.service.GetHashtagUseCase;
import com.dyno.climb.record.domain.Hashtag;
import com.dyno.climb.shared.dto.IdDto;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hashtags")
public class HashtagController {
  private final GetHashtagUseCase getHashtagUseCase;
  private final HashtagMapper hashtagMapper;
  private final CreateHashtagUseCase createHashtagUseCase;

  public HashtagController(
      GetHashtagUseCase getHashtagUseCase,
      HashtagMapper hashtagMapper,
      CreateHashtagUseCase createHashtagUseCase) {
    this.getHashtagUseCase = getHashtagUseCase;
    this.hashtagMapper = hashtagMapper;
    this.createHashtagUseCase = createHashtagUseCase;
  }

  @GetMapping()
  public ResponseEntity<HashtagResponse> getHashtags(
      @AuthenticationPrincipal User user, @RequestParam("keyword") String keyword) {
    List<Hashtag> tags = getHashtagUseCase.getTagsByKeyword(keyword);

    return ResponseEntity.ok(
        new HashtagResponse(tags.stream().map(hashtagMapper::toDto).collect(Collectors.toList())));
  }

  @PostMapping("")
  public ResponseEntity<IdDto> createHashtag(
      @AuthenticationPrincipal User user, @Valid @RequestBody CreateHashtagRequest request) {
    return ResponseEntity.status(201)
        .body(new IdDto(createHashtagUseCase.save(user, request.getName())));
  }
}

package com.dyno.climb.feature.search;

import com.dyno.climb.feature.search.dto.SearchRequest;
import com.dyno.climb.feature.search.dto.SearchResponse;
import com.dyno.climb.feature.search.service.SearchDto;
import com.dyno.climb.feature.search.service.SearchUseCase;
import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.mission.adapter.in.web.dto.*;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
public class SearchController {
  private final SearchUseCase searchUseCase;

  public SearchController(SearchUseCase searchUseCase) {
    this.searchUseCase = searchUseCase;
  }

  @PostMapping("")
  public ResponseEntity<SearchResponse> search(
      @AuthenticationPrincipal User user, @Valid @RequestBody SearchRequest request) {
    SearchDto result =
        searchUseCase.search(
            user, new SearchFilter(request.getKeyword(), request.getLat(), request.getLng()));
    return ResponseEntity.ok(
        new SearchResponse(
            result.gyms().stream().map(GymDto::of).collect(Collectors.toList()),
            result.missionGroups().stream()
                .map(MissionGroupParticipationDto::of)
                .collect(Collectors.toList())));
  }
}

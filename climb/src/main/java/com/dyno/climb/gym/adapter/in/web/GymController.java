package com.dyno.climb.gym.adapter.in.web;

import com.dyno.climb.gym.adapter.in.web.dto.GymDto;
import com.dyno.climb.gym.adapter.in.web.dto.GymResponse;
import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.gym.adapter.in.web.mapper.GymMapper;
import com.dyno.climb.gym.application.port.in.GetGymUseCase;
import com.dyno.climb.gym.domain.Gym;
import com.dyno.climb.gym.domain.command.SearchQuery;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gyms")
public class GymController {
  private final GetGymUseCase getGymUseCase;
  private final GymMapper gymMapper;

  public GymController(GetGymUseCase getGymUseCase, GymMapper gymMapper) {
    this.getGymUseCase = getGymUseCase;
    this.gymMapper = gymMapper;
  }

  @GetMapping()
  public ResponseEntity<GymResponse> getGyms(
      @AuthenticationPrincipal User user, @Valid @ModelAttribute SearchFilter filter) {
    return ResponseEntity.ok(
        new GymResponse(
            getGymUseCase
                .getByQuery(SearchQuery.of(filter.getKeyword(), filter.getLat(), filter.getLng()))
                .stream()
                .map(gymMapper::toDto)
                .collect(Collectors.toList())));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GymDto> getGymById(
      @AuthenticationPrincipal User user, @PathVariable("id") String id) {
    Gym gym = getGymUseCase.getById(id);

    return ResponseEntity.ok(gymMapper.toDto(gym));
  }
}

package com.dyno.climb.gym.adapter.in.web;

import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemDto;
import com.dyno.climb.gym.adapter.in.web.dto.BoulderProblemResponse;
import com.dyno.climb.gym.adapter.in.web.dto.request.BoulderProblemFilter;
import com.dyno.climb.gym.adapter.in.web.mapper.BoulderProblemMapper;
import com.dyno.climb.gym.application.port.in.GetBoulderProblemUseCase;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.command.BoulderProblemQuery;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boulder-problems")
public class BoulderProblemController {
  private final GetBoulderProblemUseCase getBoulderProblemUseCase;
  private final BoulderProblemMapper boulderProblemMapper;

  public BoulderProblemController(
      GetBoulderProblemUseCase getBoulderProblemUseCase,
      BoulderProblemMapper boulderProblemMapper) {
    this.getBoulderProblemUseCase = getBoulderProblemUseCase;
    this.boulderProblemMapper = boulderProblemMapper;
  }

  @GetMapping()
  public ResponseEntity<BoulderProblemResponse> getBoulderProblems(
      @AuthenticationPrincipal User user, @Valid @ModelAttribute BoulderProblemFilter filter) {
    List<BoulderProblem> boulderProblems =
        getBoulderProblemUseCase.getByQuery(
            new BoulderProblemQuery(filter.getKeyword(), filter.getGymId()));

    return ResponseEntity.ok(
        new BoulderProblemResponse(
            boulderProblems.stream()
                .map(boulderProblemMapper::toDto)
                .collect(Collectors.toList())));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BoulderProblemDto> getBoulderProblemById(
      @AuthenticationPrincipal User user, @PathVariable("id") String id) {
    BoulderProblem boulderProblem = getBoulderProblemUseCase.getById(id);

    return ResponseEntity.ok(boulderProblemMapper.toDto(boulderProblem));
  }
}

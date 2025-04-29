package com.dyno.climb.feature.home;

import com.dyno.climb.feature.home.dto.HomeResponse;
import com.dyno.climb.feature.home.service.GetHomeUseCase;
import com.dyno.climb.feature.home.service.HomeDto;
import com.dyno.climb.feature.search.dto.SearchRequest;
import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
public class HomeController {
  private final GetHomeUseCase getHomeUseCase;

  public HomeController(GetHomeUseCase getHomeUseCase) {
    this.getHomeUseCase = getHomeUseCase;
  }

  @PostMapping("")
  public ResponseEntity<HomeResponse> getHome(
      @AuthenticationPrincipal User user, @Valid @RequestBody SearchRequest request) {
    HomeDto result =
        getHomeUseCase.get(
            user, new SearchFilter(request.getKeyword(), request.getLat(), request.getLng()));

    return ResponseEntity.ok(HomeResponse.of(result));
  }
}

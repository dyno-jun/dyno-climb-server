package com.dyno.climb.feature.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class HealthController {

  public HealthController() {}

  @GetMapping("")
  public ResponseEntity<String> checkHealth() {
    return ResponseEntity.ok("success");
  }
}

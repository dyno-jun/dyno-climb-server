package com.dyno.climb.user.adapter.in.web;

import com.dyno.climb.user.adapter.in.web.dto.request.LoginUserDto;
import com.dyno.climb.user.adapter.in.web.dto.request.RegisterUserDto;
import com.dyno.climb.user.adapter.in.web.dto.response.LoginResponse;
import com.dyno.climb.user.application.port.in.LoginUseCase;
import com.dyno.climb.user.application.port.in.RegisterUseCase;
import com.dyno.climb.user.domain.command.CreateUser;
import com.dyno.climb.user.domain.vo.Token;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
  private final RegisterUseCase registerUseCase;
  private final LoginUseCase loginUseCase;

  public AuthController(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
    this.registerUseCase = registerUseCase;
    this.loginUseCase = loginUseCase;
  }

  @PostMapping("/api/register")
  public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDto user) {
    registerUseCase.register(
        new CreateUser(
            user.getAccountId(),
            user.getPassword(),
            user.getNickName(),
            user.getStartDate(),
            user.getReach()));

    return ResponseEntity.status(201).body("User registered successfully");
  }

  @PostMapping("/api/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginUserDto loginRequest) {
    Token token = loginUseCase.login(loginRequest.getAccountId(), loginRequest.getPassword());

    return ResponseEntity.ok(new LoginResponse(token.accessToken(), token.refreshToken()));
  }
}

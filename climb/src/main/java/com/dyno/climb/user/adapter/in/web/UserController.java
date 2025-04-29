package com.dyno.climb.user.adapter.in.web;

import com.dyno.climb.user.adapter.in.web.dto.request.UpdateUserDto;
import com.dyno.climb.user.adapter.in.web.dto.response.UserResponse;
import com.dyno.climb.user.adapter.in.web.mapper.UserMapper;
import com.dyno.climb.user.application.port.in.LogoutUseCase;
import com.dyno.climb.user.application.port.in.UpdateUserUseCase;
import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.command.UpdateUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final LogoutUseCase logoutUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final UserMapper userMapper;

  public UserController(
      LogoutUseCase logoutUseCase, UpdateUserUseCase updateUserUseCase, UserMapper userMapper) {
    this.logoutUseCase = logoutUseCase;
    this.updateUserUseCase = updateUserUseCase;
    this.userMapper = userMapper;
  }

  @PostMapping("/me/logout")
  public ResponseEntity<?> logout(@AuthenticationPrincipal User user) {
    logoutUseCase.logout(user);
    return ResponseEntity.ok("User logout out successfully");
  }

  @PostMapping("/me/revoke")
  public ResponseEntity<?> revoke(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok("User revoke successfully");
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> get(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(new UserResponse(userMapper.toDto(user)));
  }

  @PutMapping("/me")
  public ResponseEntity<?> update(
      @AuthenticationPrincipal User user, @Valid @RequestBody UpdateUserDto userDto) {
    updateUserUseCase.update(
        user, new UpdateUser(userDto.getNickName(), userDto.getStartDate(), userDto.getReach()));
    return ResponseEntity.ok("User update successfully");
  }
}

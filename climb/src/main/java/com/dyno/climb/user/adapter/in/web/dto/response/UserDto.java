package com.dyno.climb.user.adapter.in.web.dto.response;

import com.dyno.climb.user.domain.User;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
  private String nickName;
  private LocalDate startDate;
  private int reach;
  private String accountId;
  private UserLevelDto userLevel;

  public static UserDto create(String userId) {
    return new UserDto("nickName", LocalDate.now(), 100, userId, new UserLevelDto(1, 100, "1"));
  }

  public static UserDto create(User user) {
    return new UserDto(
        user.getNickName(),
        user.getStartDate(),
        user.getReach(),
        user.getAccountId(),
        UserLevelDto.of(user.getLevel()));
  }

  public static UserDto of(User user) {
    return new UserDto(
        user.getNickName(),
        user.getStartDate(),
        user.getReach(),
        user.getAccountId(),
        UserLevelDto.of(user.getLevel()));
  }
}

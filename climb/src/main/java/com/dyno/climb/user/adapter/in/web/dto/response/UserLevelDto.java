package com.dyno.climb.user.adapter.in.web.dto.response;

import com.dyno.climb.user.domain.UserLevel;

public class UserLevelDto {
  private final int currentExp;
  private final int levelUpExp;
  private final String level;

  public UserLevelDto(int currentExp, int levelUpExp, String level) {
    this.currentExp = currentExp;
    this.levelUpExp = levelUpExp;
    this.level = level;
  }

  public static UserLevelDto of(UserLevel userLevel) {
    return new UserLevelDto(userLevel.currentExp(), userLevel.levelUpExp(), userLevel.level());
  }

  public int getCurrentExp() {
    return currentExp;
  }

  public int getLevelUpExp() {
    return levelUpExp;
  }

  public String getLevel() {
    return level;
  }
}

package com.dyno.climb.user.adapter.out.persistence.db.entity;

import jakarta.persistence.*;

@Embeddable
public class UserLevelDto {
  private int currentExp;
  private int levelUpExp;
  private String level;

  public UserLevelDto() {}

  public UserLevelDto(int currentExp, int levelUpExp, String level) {
    this.currentExp = currentExp;
    this.levelUpExp = levelUpExp;
    this.level = level;
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

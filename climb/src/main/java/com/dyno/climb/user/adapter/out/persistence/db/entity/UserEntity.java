package com.dyno.climb.user.adapter.out.persistence.db.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @Column(unique = true)
  private String accountId;

  private String password;
  private String nickName;
  private LocalDate startDate;
  private int reach;
  @Embedded private UserLevelDto level;

  public UserEntity() {}

  public UserEntity(
      Long id,
      String uid,
      String accountId,
      String password,
      String nickName,
      LocalDate startDate,
      int reach,
      UserLevelDto level) {
    this.id = id;
    this.uid = uid;
    this.accountId = accountId;
    this.password = password;
    this.nickName = nickName;
    this.startDate = startDate;
    this.reach = reach;
    this.level = level;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getPassword() {
    return password;
  }

  public String getNickName() {
    return nickName;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public int getReach() {
    return reach;
  }

  public UserLevelDto getLevel() {
    return level;
  }
}

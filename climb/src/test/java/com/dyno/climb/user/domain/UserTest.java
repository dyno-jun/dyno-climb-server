package com.dyno.climb.user.domain;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  public static User create() {
    return new User(
        "userId",
        "dyno@mgail.com",
        new UserLevel(0, 100, "starter"),
        "USER",
        "1234",
        "nickName",
        LocalDate.now(),
        100);
  }
}

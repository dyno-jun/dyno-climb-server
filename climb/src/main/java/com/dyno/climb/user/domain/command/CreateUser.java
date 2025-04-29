package com.dyno.climb.user.domain.command;

import java.time.LocalDate;

public record CreateUser(
    String accountId, String password, String nickName, LocalDate startDate, Integer reach) {}

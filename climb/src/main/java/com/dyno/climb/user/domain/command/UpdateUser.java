package com.dyno.climb.user.domain.command;

import java.time.LocalDate;

public record UpdateUser(String nickName, LocalDate startDate, Integer reach) {}

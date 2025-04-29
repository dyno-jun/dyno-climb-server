package com.dyno.climb.user.application.port.in;

import com.dyno.climb.user.domain.vo.Token;

public interface LoginUseCase {
  Token login(String accountId, String password);
}

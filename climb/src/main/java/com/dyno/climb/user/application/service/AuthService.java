package com.dyno.climb.user.application.service;

import com.dyno.climb.shared.error.UserAlreadyExistsError;
import com.dyno.climb.shared.error.UserNotExistsError;
import com.dyno.climb.shared.utils.IDGenerator;
import com.dyno.climb.user.application.port.out.UserPort;
import com.dyno.climb.user.application.port.in.LoginUseCase;
import com.dyno.climb.user.application.port.in.RegisterUseCase;
import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.UserLevel;
import com.dyno.climb.user.domain.command.CreateUser;
import com.dyno.climb.user.domain.service.JwtUtil;
import com.dyno.climb.user.domain.vo.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements LoginUseCase, RegisterUseCase {

  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  private final UserPort userPort;

  public AuthService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserPort userPort) {
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
    this.userPort = userPort;
  }

  @Override
  public Token login(String accountId, String password) {
    User user = userPort.findByAccountId(accountId);
    if (user == null) {
      throw new UserNotExistsError("Username not exists");
    }
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new UserNotExistsError("invalid password");
    }

    return new Token(jwtUtil.generateToken(accountId), jwtUtil.generateToken(accountId));
  }

  @Override
  public void register(CreateUser createUser) {
    User user = userPort.findByAccountId(createUser.accountId());
    if (user != null) {
      throw new UserAlreadyExistsError("Username already exists");
    }

    userPort.save(
        new User(
            IDGenerator.generate(),
            createUser.accountId(),
            new UserLevel(0, 0, "first"),
            "USER",
            passwordEncoder.encode(createUser.password()),
            createUser.nickName(),
            createUser.startDate(),
            createUser.reach()));
  }
}

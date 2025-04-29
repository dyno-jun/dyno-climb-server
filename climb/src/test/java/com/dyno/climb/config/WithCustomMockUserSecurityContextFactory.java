package com.dyno.climb.config;

import com.dyno.climb.user.domain.User;
import com.dyno.climb.user.domain.UserLevel;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithCustomMockUserSecurityContextFactory
    implements WithSecurityContextFactory<WithCustomMockUser> {

  @Override
  public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
    String role = annotation.role();

    // 여기서 바인딩되어 반환할 객체를 정의해주면 됩니다
    User user =
        new User(
            "userId",
            "dyno@mgail.com",
            new UserLevel(0, 100, "starter"),
            "USER",
            "1234",
            "nickName",
            LocalDate.now(),
            100);

    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(
            user, "password", List.of(new SimpleGrantedAuthority(role)));
    SecurityContext context = SecurityContextHolder.getContext();
    context.setAuthentication(token);
    return context;
  }
}

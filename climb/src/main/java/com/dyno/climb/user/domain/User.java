package com.dyno.climb.user.domain;

import com.dyno.climb.user.domain.command.UpdateUser;
import jakarta.annotation.Nullable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
public class User implements UserDetails {

  private final String id;
  private final String accountId;
  private final UserLevel level;
  private final String role;
  private String password;
  private String nickName;
  @Nullable private LocalDate startDate;
  @Nullable private Integer reach;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  @Override
  public String getUsername() {
    return accountId;
  }

  public void update(UpdateUser updateUser) {
    this.reach = updateUser.reach();
    this.nickName = updateUser.nickName();
    this.startDate = updateUser.startDate();
  }
}

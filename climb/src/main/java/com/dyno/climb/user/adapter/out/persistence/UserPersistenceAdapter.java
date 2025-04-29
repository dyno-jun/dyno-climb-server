package com.dyno.climb.user.adapter.out.persistence;

import com.dyno.climb.shared.utils.IDGenerator;
import com.dyno.climb.user.adapter.out.persistence.db.UserRepository;
import com.dyno.climb.user.adapter.out.persistence.db.entity.UserEntity;
import com.dyno.climb.user.adapter.out.persistence.db.entity.UserLevelDto;
import com.dyno.climb.user.application.port.out.UserPort;
import com.dyno.climb.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements UserPort {
  private final UserRepository userRepository;
  private final UserMapper mapper;

  public UserPersistenceAdapter(UserRepository userRepository, UserMapper mapper) {
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  public User findByAccountId(String accountId) {
    return mapper.mapToUser(userRepository.findByAccountId(accountId));
  }

  @Override
  public void save(User user) {
    UserEntity userEntity = userRepository.findByAccountId(user.getAccountId());

    userRepository.save(
        new UserEntity(
            userEntity == null ? 0L : userEntity.getId(),
            IDGenerator.generate(),
            user.getAccountId(),
            user.getPassword(),
            user.getNickName(),
            user.getStartDate(),
            user.getReach() == null ? 0 : user.getReach(),
            user.getLevel() == null
                ? null
                : new UserLevelDto(
                    user.getLevel().currentExp(),
                    user.getLevel().levelUpExp(),
                    user.getLevel().level())));
  }

  @Override
  public List<User> findByIds(List<String> ids) {
    return userRepository.findByUidIn(ids).stream()
        .map(mapper::mapToUser)
        .collect(Collectors.toList());
  }
}

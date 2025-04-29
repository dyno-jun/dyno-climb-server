package com.dyno.climb.record.adapter.out.persistence;

import com.dyno.climb.record.adapter.out.persistence.db.HashTagRepository;
import com.dyno.climb.record.adapter.out.persistence.db.entity.HashTagEntity;
import com.dyno.climb.record.application.port.HashtagPort;
import com.dyno.climb.record.domain.Hashtag;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class HashtagPersistenceAdapter implements HashtagPort {
  private final HashTagRepository hashTagRepository;

  public HashtagPersistenceAdapter(HashTagRepository hashTagRepository) {
    this.hashTagRepository = hashTagRepository;
  }

  @Override
  public List<Hashtag> findByKeyword(String keyword) {
    return hashTagRepository.findByNameContaining(keyword).stream()
        .map(hashTagEntity -> new Hashtag(hashTagEntity.getUid(), hashTagEntity.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<Hashtag> findByName(String name) {
    return hashTagRepository
        .findByName(name)
        .map(hashTagEntity -> new Hashtag(hashTagEntity.getUid(), hashTagEntity.getName()));
  }

  @Override
  public void save(Hashtag record) {
    hashTagRepository.save(new HashTagEntity(0L, record.getId(), record.getName()));
  }
}

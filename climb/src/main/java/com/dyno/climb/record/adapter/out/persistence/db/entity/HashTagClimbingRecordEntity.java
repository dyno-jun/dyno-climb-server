package com.dyno.climb.record.adapter.out.persistence.db.entity;

import jakarta.persistence.*;

@Entity(name = "hash_tag_climbing_records")
public class HashTagClimbingRecordEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_uid", referencedColumnName = "uid")
  private ClimbingRecordEntity record;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tag_uid", referencedColumnName = "uid")
  private HashTagEntity hashTag;

  public HashTagClimbingRecordEntity() {}

  public HashTagClimbingRecordEntity(
      Long id, String uid, ClimbingRecordEntity record, HashTagEntity hashTag) {
    this.id = id;
    this.uid = uid;
    this.record = record;
    this.hashTag = hashTag;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public ClimbingRecordEntity getRecord() {
    return record;
  }

  public HashTagEntity getHashTag() {
    return hashTag;
  }
}

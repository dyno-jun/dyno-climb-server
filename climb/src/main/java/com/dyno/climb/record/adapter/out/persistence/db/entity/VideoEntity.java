package com.dyno.climb.record.adapter.out.persistence.db.entity;

import jakarta.persistence.*;

@Entity(name = "videos")
public class VideoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id", referencedColumnName = "uid")
  private ClimbingRecordEntity record;

  private String url;

  public VideoEntity() {}

  public VideoEntity(Long id, String uid, ClimbingRecordEntity record, String url) {
    this.id = id;
    this.uid = uid;
    this.record = record;
    this.url = url;
  }

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getUid() {
    return uid;
  }

  public ClimbingRecordEntity getRecord() {
    return record;
  }
}

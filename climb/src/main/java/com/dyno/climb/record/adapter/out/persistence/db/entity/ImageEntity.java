package com.dyno.climb.record.adapter.out.persistence.db.entity;

import jakarta.persistence.*;

@Entity(name = "images")
public class ImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "record_id", referencedColumnName = "uid")
  private ClimbingRecordEntity record;

  private String url;

  public ImageEntity() {}

  public ImageEntity(Long id, String uid, ClimbingRecordEntity record, String url) {
    this.id = id;
    this.uid = uid;
    this.record = record;
    this.url = url;
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

  public String getUrl() {
    return url;
  }
}

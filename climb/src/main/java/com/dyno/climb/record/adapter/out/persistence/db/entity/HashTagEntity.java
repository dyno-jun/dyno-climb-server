package com.dyno.climb.record.adapter.out.persistence.db.entity;

import jakarta.persistence.*;

@Entity(name = "hash_tags")
public class HashTagEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String uid;

  @Column(unique = true)
  private String name;

  public HashTagEntity() {}

  public HashTagEntity(Long id, String uid, String name) {
    this.id = id;
    this.uid = uid;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getUid() {
    return uid;
  }

  public String getName() {
    return name;
  }
}

package com.dyno.climb.record.domain;

public enum MissionRecordStatus {
  SUBMITTED, // The mission record has been submitted
  UNDER_REVIEW, // The mission record is currently being checked
  SUCCESSFUL, // The mission was completed successfully
  FAILED // The mission failed
}

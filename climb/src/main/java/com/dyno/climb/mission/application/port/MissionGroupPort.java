package com.dyno.climb.mission.application.port;

import com.dyno.climb.mission.domain.MissionGroup;
import java.util.List;

public interface MissionGroupPort {
  List<MissionGroup> findAll();

  List<MissionGroup> findByGymIds(List<String> gymIds);

  MissionGroup findById(String id);

  List<MissionGroup> findByIds(List<String> ids);
}

package com.dyno.climb.gym.domain;

import com.dyno.climb.gym.domain.vo.*;
import com.dyno.climb.shared.vo.Image;
import java.time.LocalDateTime;
import java.util.List;

public class GymTest {
  public static Gym create() {
    return new Gym(
        "gymId",
        "더클라임 B 홍대점",
        new Address("서울특별시 마포구 서교동 353-5 경남관광빌딩 2층", "", "04032"),
        new Location(37.5547574, 126.9202255),
        new Tel("02-332-5014", "02-332-5014"),
        new Image(
            "http://theclimb.co.kr/wp-content/uploads/2022/11/%EB%AC%B4%EC%A0%9C-19_%EB%8C%80%EC%A7%80-1-01.jpg"),
        new Link("https://www.instagram.com/theclimb_b_hongdae/"),
        LocalDateTime.now(),
        List.of(new BoulderLevel("id", "초록", "#00FF00")));
  }
}

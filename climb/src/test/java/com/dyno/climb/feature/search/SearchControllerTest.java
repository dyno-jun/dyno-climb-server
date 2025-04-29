package com.dyno.climb.feature.search;

import static com.dyno.climb.config.ApiDocumentUtils.getDocumentRequest;
import static com.dyno.climb.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dyno.climb.feature.search.dto.SearchRequest;
import com.dyno.climb.feature.search.service.SearchDto;
import com.dyno.climb.feature.search.service.SearchUseCase;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.GymTest;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.domain.MissionGroupParticipationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
class SearchControllerTest {
  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;
  @MockBean private SearchUseCase searchUseCase;

  @BeforeEach
  void setUp(
      final WebApplicationContext context,
      final RestDocumentationContextProvider restDocumentation) {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocumentation))
            .alwaysDo(MockMvcResultHandlers.print())
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
  }

  @Test
  void search() throws Exception {
    SearchRequest request = new SearchRequest("keyword", 37.12341, 121.122213);
    given(searchUseCase.search(any(), any()))
        .willReturn(
            new SearchDto(
                List.of(GymTest.create()),
                List.of(
                    GymMissionGroupDto.of(
                        Map.of("gymId", GymTest.create()),
                        Map.of("boulderProblemId", BoulderProblem.create()),
                        MissionGroupParticipationTest.create("missionGroupId")))));

    // when & then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.post("/api/search")
                .header("Authorization", "Bearer some-auth-token")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "search",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                requestFields(
                    fieldWithPath("keyword").description("키워드").optional(),
                    fieldWithPath("lat").description("위도").optional(),
                    fieldWithPath("lng").description("경도").optional()),
                responseFields(
                    fieldWithPath("gyms[]").description("암장 목록"),
                    fieldWithPath("gyms[].id").description("암장 고유 아이디"),
                    fieldWithPath("gyms[].name").description("암장명"),
                    fieldWithPath("gyms[].address").description("암장 주소"),
                    fieldWithPath("gyms[].latitude").description("위도"),
                    fieldWithPath("gyms[].longitude").description("경도"),
                    fieldWithPath("gyms[].tel").description("전화번호"),
                    fieldWithPath("gyms[].image").description("로고 이미지"),
                    fieldWithPath("gyms[].link").description("홈페이지 주소"),
                    fieldWithPath("gyms[].updatedAt").description("업데이트 날짜"),
                    fieldWithPath("gyms[].levels[]").description("암장 볼더링 레벨 목록"),
                    fieldWithPath("gyms[].levels[].name").description("레벨 명"),
                    fieldWithPath("gyms[].levels[].color").description("레벨 색상코드"),
                    fieldWithPath("missionGroups[]").description("미션 그룹 목록"),
                    fieldWithPath("missionGroups[].missionGroup").description("미션 그룹"),
                    fieldWithPath("missionGroups[].missionGroup.id").description("미션 그룹"),
                    fieldWithPath("missionGroups[].missionGroup.gym").description("암장 참조"),
                    fieldWithPath("missionGroups[].missionGroup.gym.id").description("암장 고유 아이디"),
                    fieldWithPath("missionGroups[].missionGroup.gym.name").description("암장명"),
                    fieldWithPath("missionGroups[].missionGroup.gym.address").description("암장 주소"),
                    fieldWithPath("missionGroups[].missionGroup.gym.latitude").description("위도"),
                    fieldWithPath("missionGroups[].missionGroup.gym.longitude").description("경도"),
                    fieldWithPath("missionGroups[].missionGroup.gym.tel").description("전화번호"),
                    fieldWithPath("missionGroups[].missionGroup.gym.image").description("로고 이미지"),
                    fieldWithPath("missionGroups[].missionGroup.gym.link").description("홈페이지 주소"),
                    fieldWithPath("missionGroups[].missionGroup.gym.updatedAt")
                        .description("업데이트 날짜"),
                    fieldWithPath("missionGroups[].missionGroup.gym.levels[]")
                        .description("암장 볼더링 레벨 목록"),
                    fieldWithPath("missionGroups[].missionGroup.gym.levels[].name")
                        .description("레벨 명"),
                    fieldWithPath("missionGroups[].missionGroup.gym.levels[].color")
                        .description("레벨 색상코드"),
                    fieldWithPath("missionGroups[].missionGroup.title").description("제목"),
                    fieldWithPath("missionGroups[].missionGroup.description").description("설명"),
                    fieldWithPath("missionGroups[].missionGroup.image").description("이미지"),
                    fieldWithPath("missionGroups[].missionGroup.type").description("타입"),
                    fieldWithPath("missionGroups[].missionGroup.totalExp").description("전체 경험치"),
                    fieldWithPath("missionGroups[].missionGroup.period").description("미션 기간"),
                    fieldWithPath("missionGroups[].missionGroup.period.startAt").description("시작일"),
                    fieldWithPath("missionGroups[].missionGroup.period.endAt").description("종료일"),
                    fieldWithPath("missionGroups[].userMissionGroup")
                        .description("사용자 미션그룹 참여 정보")
                        .optional(),
                    fieldWithPath("missionGroups[].userMissionGroup.summary")
                        .description("사용자 미션그룹 참여 정보 요약"),
                    fieldWithPath("missionGroups[].userMissionGroup.summary.totalCnt")
                        .description("전체 미션 수"),
                    fieldWithPath("missionGroups[].userMissionGroup.summary.completedCnt")
                        .description("완료한 미션 수"),
                    fieldWithPath("missionGroups[].userMissionGroup.summary.completed")
                        .description("전체 완료 여부"),
                    fieldWithPath("missionGroups[].missions[]").description("미션 목록"),
                    fieldWithPath("missionGroups[].missions[].mission").description("미션"),
                    fieldWithPath("missionGroups[].missions[].mission.id").description("미션 고유 아이디"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem")
                        .description("볼더링 문제 참조"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem.id")
                        .description("볼더링 고유 아이디"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem.sector")
                        .description("섹터명 - 연남"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem.level.name")
                        .description("레벨명"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem.level.color")
                        .description("레벨 색상 코드"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem.image")
                        .description("이미지"),
                    fieldWithPath("missionGroups[].missions[].mission.boulderProblem.gymId")
                        .description("Gym 고유 아이디"),
                    fieldWithPath("missionGroups[].missions[].mission.title").description("제목"),
                    fieldWithPath("missionGroups[].missions[].mission.description")
                        .description("설명"),
                    fieldWithPath("missionGroups[].missions[].mission.image").description("이미지"),
                    fieldWithPath("missionGroups[].missions[].mission.type").description("미션 유형"),
                    fieldWithPath("missionGroups[].missions[].mission.exp").description("획득 경험치"),
                    fieldWithPath("missionGroups[].missions[].userMission")
                        .description("사용자 미션 참여 정보")
                        .optional(),
                    fieldWithPath("missionGroups[].missions[].userMission.summary")
                        .description("사용자 미션 참여 정보 요약"),
                    fieldWithPath("missionGroups[].missions[].userMission.summary.tryCnt")
                        .description("미션 시도 횟수"),
                    fieldWithPath("missionGroups[].missions[].userMission.summary.status")
                        .description("미션 상태"))));
  }
}

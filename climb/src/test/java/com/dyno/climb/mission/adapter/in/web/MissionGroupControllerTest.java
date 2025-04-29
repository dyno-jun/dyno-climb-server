package com.dyno.climb.mission.adapter.in.web;

import static com.dyno.climb.config.ApiDocumentUtils.getDocumentRequest;
import static com.dyno.climb.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dyno.climb.config.WithCustomMockUser;
import com.dyno.climb.gym.adapter.in.web.dto.request.QueryParams;
import com.dyno.climb.gym.domain.BoulderProblem;
import com.dyno.climb.gym.domain.GymTest;
import com.dyno.climb.mission.adapter.in.web.dto.request.MissionGroupSearchFilter;
import com.dyno.climb.mission.application.service.GetMissionGroupUseCase;
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.application.service.ParticipateMissionUseCase;
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
class MissionGroupControllerTest {

  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private GetMissionGroupUseCase getMissionGroupUseCase;
  @MockBean private ParticipateMissionUseCase participateMissionUseCase;

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
  @WithCustomMockUser
  void getMissionGroups() throws Exception {
    QueryParams searchFilter =
        new MissionGroupSearchFilter("keyword", 37.12341, 121.122213, "gymId");
    given(getMissionGroupUseCase.getByQuery(any(), any()))
        .willReturn(
            List.of(
                GymMissionGroupDto.of(
                    Map.of("gymId", GymTest.create()),
                    Map.of("boulderProblemId", BoulderProblem.create()),
                    MissionGroupParticipationTest.create("missionGroupId"))));

    // when & then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get(
                    "/api/mission-groups" + searchFilter.getQueryString())
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-mission-group-list",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                queryParameters(
                    parameterWithName("keyword").description("키워드").optional(),
                    parameterWithName("lat").description("위도").optional(),
                    parameterWithName("lng").description("경도").optional()),
                responseFields(
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

  @Test
  @WithCustomMockUser
  void getMissionGroupById() throws Exception {
    String id = "missionGroupId";
    given(getMissionGroupUseCase.getById(any(), any()))
        .willReturn(
            GymMissionGroupDto.of(
                Map.of("gymId", GymTest.create()),
                Map.of("boulderProblemId", BoulderProblem.create()),
                MissionGroupParticipationTest.create("missionGroupId")));

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/mission-groups/{id}", id)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-mission-group",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("아이디")),
                responseFields(
                    fieldWithPath("missionGroup").description("미션 그룹"),
                    fieldWithPath("missionGroup.id").description("미션 그룹"),
                    fieldWithPath("missionGroup.gym").description("암장 참조"),
                    fieldWithPath("missionGroup.gym.id").description("암장 고유 아이디"),
                    fieldWithPath("missionGroup.gym.name").description("암장명"),
                    fieldWithPath("missionGroup.gym.address").description("암장 주소"),
                    fieldWithPath("missionGroup.gym.latitude").description("위도"),
                    fieldWithPath("missionGroup.gym.longitude").description("경도"),
                    fieldWithPath("missionGroup.gym.tel").description("전화번호"),
                    fieldWithPath("missionGroup.gym.image").description("로고 이미지"),
                    fieldWithPath("missionGroup.gym.link").description("홈페이지 주소"),
                    fieldWithPath("missionGroup.gym.updatedAt").description("업데이트 날짜"),
                    fieldWithPath("missionGroup.gym.levels[]").description("암장 볼더링 레벨 목록"),
                    fieldWithPath("missionGroup.gym.levels[].name").description("레벨 명"),
                    fieldWithPath("missionGroup.gym.levels[].color").description("레벨 색상코드"),
                    fieldWithPath("missionGroup.title").description("제목"),
                    fieldWithPath("missionGroup.description").description("설명"),
                    fieldWithPath("missionGroup.image").description("이미지"),
                    fieldWithPath("missionGroup.type").description("타입"),
                    fieldWithPath("missionGroup.totalExp").description("전체 경험치"),
                    fieldWithPath("missionGroup.period").description("미션 기간"),
                    fieldWithPath("missionGroup.period.startAt").description("시작일"),
                    fieldWithPath("missionGroup.period.endAt").description("종료일"),
                    fieldWithPath("userMissionGroup").description("사용자 미션그룹 참여 정보").optional(),
                    fieldWithPath("userMissionGroup.summary").description("사용자 미션그룹 참여 정보 요약"),
                    fieldWithPath("userMissionGroup.summary.totalCnt").description("전체 미션 수"),
                    fieldWithPath("userMissionGroup.summary.completedCnt").description("완료한 미션 수"),
                    fieldWithPath("userMissionGroup.summary.completed").description("전체 완료 여부"),
                    fieldWithPath("missions[]").description("미션 목록"),
                    fieldWithPath("missions[].mission").description("미션"),
                    fieldWithPath("missions[].mission.id").description("미션 고유 아이디"),
                    fieldWithPath("missions[].mission.boulderProblem").description("볼더링 문제 참조"),
                    fieldWithPath("missions[].mission.boulderProblem.id").description("볼더링 고유 아이디"),
                    fieldWithPath("missions[].mission.boulderProblem.sector")
                        .description("섹터명 - 연남"),
                    fieldWithPath("missions[].mission.boulderProblem.level.name")
                        .description("레벨명"),
                    fieldWithPath("missions[].mission.boulderProblem.level.color")
                        .description("레벨 색상 코드"),
                    fieldWithPath("missions[].mission.boulderProblem.image").description("이미지"),
                    fieldWithPath("missions[].mission.boulderProblem.gymId")
                        .description("Gym 고유 아이디"),
                    fieldWithPath("missions[].mission.title").description("제목"),
                    fieldWithPath("missions[].mission.description").description("설명"),
                    fieldWithPath("missions[].mission.image").description("이미지"),
                    fieldWithPath("missions[].mission.type").description("미션 유형"),
                    fieldWithPath("missions[].mission.exp").description("획득 경험치"),
                    fieldWithPath("missions[].userMission").description("사용자 미션 참여 정보").optional(),
                    fieldWithPath("missions[].userMission.summary").description("사용자 미션 참여 정보 요약"),
                    fieldWithPath("missions[].userMission.summary.tryCnt").description("미션 시도 횟수"),
                    fieldWithPath("missions[].userMission.summary.status").description("미션 상태"))));
  }

  @Test
  @WithCustomMockUser
  void participateMission() throws Exception {
    String id = "missionGroupId";
    String missionId = "missionId";
    // when & then
    //    given(participateMissionUseCase.participate(any(), id, missionId)).willReturn(any());

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.patch(
                    "/api/mission-groups/{id}/missions/{missionId}/participation", id, missionId)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "patch-participate-mission",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(
                    parameterWithName("id").description("아이디"),
                    parameterWithName("missionId").description("mission id"))));
  }

  @Test
  @WithCustomMockUser
  void getMission() throws Exception {
    String id = "missionGroupId";
    String missionId = "missionId";
    // when & then
    given(getMissionGroupUseCase.getMission(any(), any(), any()))
        .willReturn(
            GymMissionGroupDto.of(
                Map.of("gymId", GymTest.create()),
                Map.of("boulderProblemId", BoulderProblem.create()),
                MissionGroupParticipationTest.create("missionGroupId")));

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get(
                    "/api/mission-groups/{id}/missions/{missionId}", id, missionId)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-mission",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(
                    parameterWithName("id").description("아이디"),
                    parameterWithName("missionId").description("mission id")),
                responseFields(
                    fieldWithPath("missionGroup").description("미션 그룹"),
                    fieldWithPath("missionGroup.id").description("미션 그룹"),
                    fieldWithPath("missionGroup.gym").description("암장 참조"),
                    fieldWithPath("missionGroup.gym.id").description("암장 고유 아이디"),
                    fieldWithPath("missionGroup.gym.name").description("암장명"),
                    fieldWithPath("missionGroup.gym.address").description("암장 주소"),
                    fieldWithPath("missionGroup.gym.latitude").description("위도"),
                    fieldWithPath("missionGroup.gym.longitude").description("경도"),
                    fieldWithPath("missionGroup.gym.tel").description("전화번호"),
                    fieldWithPath("missionGroup.gym.image").description("로고 이미지"),
                    fieldWithPath("missionGroup.gym.link").description("홈페이지 주소"),
                    fieldWithPath("missionGroup.gym.updatedAt").description("업데이트 날짜"),
                    fieldWithPath("missionGroup.gym.levels[]").description("암장 볼더링 레벨 목록"),
                    fieldWithPath("missionGroup.gym.levels[].name").description("레벨 명"),
                    fieldWithPath("missionGroup.gym.levels[].color").description("레벨 색상코드"),
                    fieldWithPath("missionGroup.title").description("제목"),
                    fieldWithPath("missionGroup.description").description("설명"),
                    fieldWithPath("missionGroup.image").description("이미지"),
                    fieldWithPath("missionGroup.type").description("타입"),
                    fieldWithPath("missionGroup.totalExp").description("전체경험치"),
                    fieldWithPath("missionGroup.period").description("미션 기간"),
                    fieldWithPath("missionGroup.period.startAt").description("시작일"),
                    fieldWithPath("missionGroup.period.endAt").description("종료일"),
                    fieldWithPath("userMissionGroup").description("사용자 미션그룹 참여 정보").optional(),
                    fieldWithPath("userMissionGroup.summary").description("사용자 미션그룹 참여 정보 요약"),
                    fieldWithPath("userMissionGroup.summary.totalCnt").description("전체 미션 수"),
                    fieldWithPath("userMissionGroup.summary.completedCnt").description("완료한 미션 수"),
                    fieldWithPath("userMissionGroup.summary.completed").description("전체 완료 여부"),
                    fieldWithPath("mission").description("미션"),
                    fieldWithPath("mission.mission.id").description("미션 고유 아이디"),
                    fieldWithPath("mission.mission.boulderProblem").description("볼더링 문제 참조"),
                    fieldWithPath("mission.mission.boulderProblem.id").description("볼더링 고유 아이디"),
                    fieldWithPath("mission.mission.boulderProblem.sector").description("섹터명 - 연남"),
                    fieldWithPath("mission.mission.boulderProblem.level.name").description("레벨명"),
                    fieldWithPath("mission.mission.boulderProblem.level.color")
                        .description("레벨 색상 코드"),
                    fieldWithPath("mission.mission.boulderProblem.image").description("이미지"),
                    fieldWithPath("mission.mission.boulderProblem.gymId").description("Gym 고유 아이디"),
                    fieldWithPath("mission.mission.title").description("제목"),
                    fieldWithPath("mission.mission.description").description("설명"),
                    fieldWithPath("mission.mission.image").description("이미지"),
                    fieldWithPath("mission.mission.type").description("미션 유형"),
                    fieldWithPath("mission.mission.exp").description("획득 경험치"),
                    fieldWithPath("mission.userMission").description("사용자 미션 참여 정보").optional(),
                    fieldWithPath("mission.userMission.summary").description("사용자 미션 참여 정보 요약"),
                    fieldWithPath("mission.userMission.summary.tryCnt").description("미션 시도 횟수"),
                    fieldWithPath("mission.userMission.summary.status").description("미션 상태"))));
  }
}

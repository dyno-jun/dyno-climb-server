package com.dyno.climb.record.adapter.in.web;

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
import com.dyno.climb.mission.application.service.GymMissionGroupDto;
import com.dyno.climb.mission.domain.MissionGroupParticipationTest;
import com.dyno.climb.record.adapter.in.web.dto.request.CreateRecordRequest;
import com.dyno.climb.record.adapter.in.web.dto.request.PreSignedUrlRequest;
import com.dyno.climb.record.adapter.in.web.dto.request.RecordSearchFilter;
import com.dyno.climb.record.adapter.in.web.dto.request.SubmitMissionRecordDto;
import com.dyno.climb.record.application.service.*;
import com.dyno.climb.record.application.service.dto.MissionRecordDto;
import com.dyno.climb.record.application.service.dto.PreSignedUrlDto;
import com.dyno.climb.record.domain.ClimbingRecordTest;
import com.dyno.climb.shared.dto.ImageDto;
import com.dyno.climb.shared.dto.VideoDto;
import com.dyno.climb.user.domain.UserTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
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
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
class RecordControllerTest {

  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private GetRecordUseCase getRecordUseCase;
  @MockBean private CreateRecordUseCase createRecordUseCase;
  @MockBean private UpdateRecordUseCase updateRecordUseCase;
  @MockBean private DeleteRecordUseCase deleteRecordUseCase;
  @MockBean private SubmitMissionRecordUseCase submitMissionRecordUseCase;
  @MockBean private FileUseCase fileUseCase;

  private static ResponseFieldsSnippet getResponseFieldsSnippet() {
    return responseFields(
        fieldWithPath("records[]").description("클라이밍 기록 목록"),
        fieldWithPath("records[].mission").description("미션 참조"),
        fieldWithPath("records[].mission.mission.id").description("미션 고유 아이디"),
        fieldWithPath("records[].mission.mission.boulderProblem").description("볼더링 문제 참조"),
        fieldWithPath("records[].mission.mission.boulderProblem.id").description("볼더링 고유 아이디"),
        fieldWithPath("records[].mission.mission.boulderProblem.sector").description("섹터명 - 연남"),
        fieldWithPath("records[].mission.mission.boulderProblem.level.name").description("레벨명"),
        fieldWithPath("records[].mission.mission.boulderProblem.level.color")
            .description("레벨 색상 코드"),
        fieldWithPath("records[].mission.mission.boulderProblem.image").description("이미지"),
        fieldWithPath("records[].mission.mission.boulderProblem.gymId").description("Gym 고유 아이디"),
        fieldWithPath("records[].mission.mission.title").description("제목"),
        fieldWithPath("records[].mission.mission.description").description("설명"),
        fieldWithPath("records[].mission.mission.image").description("이미지"),
        fieldWithPath("records[].mission.mission.type").description("미션 유형"),
        fieldWithPath("records[].mission.mission.exp").description("획득 경험치"),
        fieldWithPath("records[].mission.userMission").description("사용자 미션 참여 정보").optional(),
        fieldWithPath("records[].mission.userMission.summary").description("사용자 미션 참여 정보 요약"),
        fieldWithPath("records[].mission.userMission.summary.tryCnt").description("미션 시도 횟수"),
        fieldWithPath("records[].mission.userMission.summary.status").description("미션 상태"),
        fieldWithPath("records[].record").description("클라이밍 기록"),
        fieldWithPath("records[].record.id").description("기록 고유 아이디"),
        fieldWithPath("records[].record.user").description("User 참조"),
        fieldWithPath("records[].record.user.accountId").description("이메일 아이디"),
        fieldWithPath("records[].record.user.nickName").description("닉네임"),
        fieldWithPath("records[].record.user.startDate").description("시작일").optional(),
        fieldWithPath("records[].record.user.userLevel").description("시작일").optional(),
        fieldWithPath("records[].record.user.userLevel.currentExp")
            .description("현재 경험치")
            .optional(),
        fieldWithPath("records[].record.user.userLevel.levelUpExp")
            .description("다음레벨업 경험치")
            .optional(),
        fieldWithPath("records[].record.user.userLevel.level").description("레벨").optional(),
        fieldWithPath("records[].record.user.reach").description("팔길이").optional(),
        fieldWithPath("records[].record.gym").description("Gym 참조"),
        fieldWithPath("records[].record.gym.id").description("암장 고유 아이디"),
        fieldWithPath("records[].record.gym.name").description("암장명"),
        fieldWithPath("records[].record.gym.address").description("암장 주소"),
        fieldWithPath("records[].record.gym.latitude").description("위도"),
        fieldWithPath("records[].record.gym.longitude").description("경도"),
        fieldWithPath("records[].record.gym.tel").description("전화번호"),
        fieldWithPath("records[].record.gym.image").description("로고 이미지"),
        fieldWithPath("records[].record.gym.link").description("홈페이지 주소"),
        fieldWithPath("records[].record.gym.updatedAt").description("업데이트 날짜"),
        fieldWithPath("records[].record.gym.levels[]").description("암장 볼더링 레벨 목록"),
        fieldWithPath("records[].record.gym.levels[].name").description("레벨 명"),
        fieldWithPath("records[].record.gym.levels[].color").description("레벨 색상코드"),
        fieldWithPath("records[].record.date").description("날짜"),
        fieldWithPath("records[].record.boulderProblem").description("볼더링 문제"),
        fieldWithPath("records[].record.boulderProblem.id").description("볼더링 고유 아이디"),
        fieldWithPath("records[].record.boulderProblem.sector").description("섹터명 - 연남"),
        fieldWithPath("records[].record.boulderProblem.level.name").description("레벨명"),
        fieldWithPath("records[].record.boulderProblem.level.color").description("레벨 색상 코드"),
        fieldWithPath("records[].record.boulderProblem.image").description("이미지"),
        fieldWithPath("records[].record.boulderProblem.gymId").description("Gym 고유 아이디"),
        fieldWithPath("records[].record.tryCnt").description("시도 횟수"),
        fieldWithPath("records[].record.isClimbCompleted").description("완등 여부"),
        fieldWithPath("records[].record.videos[]").description("비디오 목록"),
        fieldWithPath("records[].record.videos[].url").description("비디오 url"),
        fieldWithPath("records[].record.images[]").description("이미지 목록"),
        fieldWithPath("records[].record.images[].url").description("이미지 url"),
        fieldWithPath("records[].record.duration").description("수행 시간"),
        fieldWithPath("records[].record.memo").description("메모"),
        fieldWithPath("records[].record.hashtags[]").description("해쉬 태그"),
        fieldWithPath("records[].record.hashtags[].id").description("태그 아이디"),
        fieldWithPath("records[].record.hashtags[].name").description("태그 명"),
        fieldWithPath("records[].record.isPublic").description("공개 여부"));
  }

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
  void submitMissionRecord() throws Exception {
    SubmitMissionRecordDto request = new SubmitMissionRecordDto("missionGroupId", "missionId");
    String id = "recordId";

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.post("/api/records/{id}/mission", id)
                .header("Authorization", "Bearer some-auth-token")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(
            document(
                "submit-mission-record",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("기록 아이디")),
                requestFields(
                    fieldWithPath("missionGroupId").description("미션 그룹 아이디"),
                    fieldWithPath("missionId").description("미션 아이디"))));
  }

  @Test
  @WithCustomMockUser
  void getPublicRecords() throws Exception {
    QueryParams params =
        new RecordSearchFilter("keyword", 37.123, 121.444, "missionGroupId", "missionId");

    given(getRecordUseCase.getRecordsByQuery(any()))
        .willReturn(
            List.of(
                MissionRecordDto.create(
                    UserTest.create(),
                    GymMissionGroupDto.of(
                        Map.of("gymId", GymTest.create()),
                        Map.of("boulderProblemId", BoulderProblem.create()),
                        MissionGroupParticipationTest.create("missionGroupId")),
                    ClimbingRecordTest.create(),
                    GymTest.create(),
                    BoulderProblem.create())));
    given(fileUseCase.generatePreSignedUrl(any())).willReturn("getUrl");

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/records" + params.getQueryString())
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-record-list",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                queryParameters(
                    parameterWithName("keyword").description("키워드").optional(),
                    parameterWithName("lat").description("위도").optional(),
                    parameterWithName("lng").description("경도").optional(),
                    parameterWithName("missionGroupId").description("미션 그룹 아이디"),
                    parameterWithName("missionId").description("미션 아이디")),
                getResponseFieldsSnippet()));
  }

  @Test
  @WithCustomMockUser
  void getUserRecords() throws Exception {
    given(getRecordUseCase.getRecords(any()))
        .willReturn(
            List.of(
                MissionRecordDto.create(
                    UserTest.create(),
                    GymMissionGroupDto.of(
                        Map.of("gymId", GymTest.create()),
                        Map.of("boulderProblemId", BoulderProblem.create()),
                        MissionGroupParticipationTest.create("missionGroupId")),
                    ClimbingRecordTest.create(),
                    GymTest.create(),
                    BoulderProblem.create())));
    given(fileUseCase.generatePreSignedUrl(any())).willReturn("getUrl");

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/records/users/me")
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-user-record-list",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                getResponseFieldsSnippet()));
  }

  @Test
  @WithCustomMockUser
  void getRecordById() throws Exception {
    String id = "recordId";
    given(getRecordUseCase.getById(any(), any()))
        .willReturn(
            MissionRecordDto.create(
                UserTest.create(),
                GymMissionGroupDto.of(
                    Map.of("gymId", GymTest.create()),
                    Map.of("boulderProblemId", BoulderProblem.create()),
                    MissionGroupParticipationTest.create("missionGroupId")),
                ClimbingRecordTest.create(),
                GymTest.create(),
                BoulderProblem.create()));
    given(fileUseCase.generatePreSignedUrl(any())).willReturn("getUrl");

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/records/{id}", id)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-record",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("기록 고유 아이디")),
                responseFields(
                    fieldWithPath("mission").description("미션 참조"),
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
                    fieldWithPath("mission.userMission.summary.status").description("미션 상태"),
                    fieldWithPath("record").description("클라이밍 기록"),
                    fieldWithPath("record.id").description("기록 고유 아이디"),
                    fieldWithPath("record.user").description("User 참조"),
                    fieldWithPath("record.user.accountId").description("이메일 아이디"),
                    fieldWithPath("record.user.nickName").description("닉네임"),
                    fieldWithPath("record.user.startDate").description("시작일").optional(),
                    fieldWithPath("record.user.reach").description("팔길이").optional(),
                    fieldWithPath("record.user.userLevel").description("시작일").optional(),
                    fieldWithPath("record.user.userLevel.currentExp")
                        .description("현재 경험치")
                        .optional(),
                    fieldWithPath("record.user.userLevel.levelUpExp")
                        .description("다음레벨업 경험치")
                        .optional(),
                    fieldWithPath("record.user.userLevel.level").description("레벨").optional(),
                    fieldWithPath("record.gym").description("Gym 참조"),
                    fieldWithPath("record.gym.id").description("암장 고유 아이디"),
                    fieldWithPath("record.gym.name").description("암장명"),
                    fieldWithPath("record.gym.address").description("암장 주소"),
                    fieldWithPath("record.gym.latitude").description("위도"),
                    fieldWithPath("record.gym.longitude").description("경도"),
                    fieldWithPath("record.gym.tel").description("전화번호"),
                    fieldWithPath("record.gym.image").description("로고 이미지"),
                    fieldWithPath("record.gym.link").description("홈페이지 주소"),
                    fieldWithPath("record.gym.updatedAt").description("업데이트 날짜"),
                    fieldWithPath("record.gym.levels[]").description("암장 볼더링 레벨 목록"),
                    fieldWithPath("record.gym.levels[].name").description("레벨 명"),
                    fieldWithPath("record.gym.levels[].color").description("레벨 색상코드"),
                    fieldWithPath("record.date").description("날짜"),
                    fieldWithPath("record.boulderProblem").description("볼더링 문제"),
                    fieldWithPath("record.boulderProblem.id").description("볼더링 고유 아이디"),
                    fieldWithPath("record.boulderProblem.sector").description("섹터명 - 연남"),
                    fieldWithPath("record.boulderProblem.level.name").description("레벨명"),
                    fieldWithPath("record.boulderProblem.level.color").description("레벨 색상 코드"),
                    fieldWithPath("record.boulderProblem.image").description("이미지"),
                    fieldWithPath("record.boulderProblem.gymId").description("Gym 고유 아이디"),
                    fieldWithPath("record.tryCnt").description("시도 횟수"),
                    fieldWithPath("record.isClimbCompleted").description("완등 여부"),
                    fieldWithPath("record.videos[]").description("비디오 목록"),
                    fieldWithPath("record.videos[].url").description("비디오 url"),
                    fieldWithPath("record.images[]").description("이미지 목록"),
                    fieldWithPath("record.images[].url").description("이미지 url"),
                    fieldWithPath("record.duration").description("수행 시간"),
                    fieldWithPath("record.memo").description("메모"),
                    fieldWithPath("record.hashtags[]").description("해쉬 태그"),
                    fieldWithPath("record.hashtags[].id").description("태그 아이디"),
                    fieldWithPath("record.hashtags[].name").description("태그 명"),
                    fieldWithPath("record.isPublic").description("공개 여부"))));
  }

  @Test
  void createRecord() throws Exception {
    CreateRecordRequest request =
        new CreateRecordRequest(
            "gymId",
            LocalDateTime.now(),
            "boulderProblemId",
            10,
            true,
            List.of(new VideoDto("url")),
            List.of(new ImageDto("url")),
            10,
            "memo",
            List.of("hash"),
            true);
    given(createRecordUseCase.save(any(), any())).willReturn("recordId");

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.post("/api/records")
                .header("Authorization", "Bearer some-auth-token")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(
            document(
                "create-record",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                requestFields(
                    fieldWithPath("gymId").description("암장 아이디"),
                    fieldWithPath("date").description("날짜"),
                    fieldWithPath("boulderProblemId").description("boulderProblem 아이디"),
                    fieldWithPath("tryCnt").description("시도 횟수"),
                    fieldWithPath("isClimbCompleted").description("완등 여부"),
                    fieldWithPath("videos[]").description("비디오 목록"),
                    fieldWithPath("videos[].url").description("비디오 url"),
                    fieldWithPath("images[]").description("이미지 목록"),
                    fieldWithPath("images[].url").description("이미지 url"),
                    fieldWithPath("duration").description("수행 시간"),
                    fieldWithPath("memo").description("메모"),
                    fieldWithPath("hashtagIds[]").description("해쉬 태그 아이디 목록"),
                    fieldWithPath("isPublic").description("공개 여부")),
                responseFields(fieldWithPath("id").description("record Id"))));
  }

  @Test
  void updateRecord() throws Exception {
    String id = "recordId";

    CreateRecordRequest request =
        new CreateRecordRequest(
            "gymId",
            LocalDateTime.now(),
            "buoderProblemId",
            10,
            true,
            List.of(new VideoDto("url")),
            List.of(new ImageDto("url")),
            10,
            "memo",
            List.of("hash"),
            true);

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.put("/api/records/{id}", id)
                .header("Authorization", "Bearer some-auth-token")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "update-record",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("기록 고유 아이디")),
                requestFields(
                    fieldWithPath("gymId").description("암장 아이디"),
                    fieldWithPath("date").description("날짜"),
                    fieldWithPath("boulderProblemId").description("boulderProblem 아이디"),
                    fieldWithPath("tryCnt").description("시도 횟수"),
                    fieldWithPath("isClimbCompleted").description("완등 여부"),
                    fieldWithPath("videos[]").description("비디오 목록"),
                    fieldWithPath("videos[].url").description("비디오 url"),
                    fieldWithPath("images[]").description("이미지 목록"),
                    fieldWithPath("images[].url").description("이미지 url"),
                    fieldWithPath("duration").description("수행 시간"),
                    fieldWithPath("memo").description("메모"),
                    fieldWithPath("hashtagIds[]").description("해쉬 태그 아이디 목록"),
                    fieldWithPath("isPublic").description("공개 여부"))));
  }

  @Test
  void deleteRecord() throws Exception {
    String id = "recordId";

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.delete("/api/records/{id}", id)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "delete-record",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("기록 고유 아이디"))));
  }

  @Test
  void getPreSignedUrl() throws Exception {
    PreSignedUrlRequest request = new PreSignedUrlRequest("fileName");
    given(fileUseCase.generateUploadPreSignedUrl(any(), any()))
        .willReturn(new PreSignedUrlDto("presignedUrl", "filePath"));

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.put("/api/records/media/presigned-url")
                .header("Authorization", "Bearer some-auth-token")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-presigned-url",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                requestFields(fieldWithPath("fileName").description("파일명")),
                responseFields(
                    fieldWithPath("preSignedUrl").description("업로드 가능한 s3 url"),
                    fieldWithPath("filePath").description("파일 상대 경로"))));
  }
}

package com.dyno.climb.gym.adapter.in.web;

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

import com.dyno.climb.gym.adapter.in.web.dto.request.SearchFilter;
import com.dyno.climb.gym.application.port.in.GetGymUseCase;
import com.dyno.climb.gym.domain.GymTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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
class GymControllerTest {
  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private GetGymUseCase getGymUseCase;

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
  void getGyms() throws Exception {
    SearchFilter searchFilter = new SearchFilter("keyword", 37.12341, 121.122213);
    // given
    given(getGymUseCase.getByQuery(any())).willReturn(List.of(GymTest.create()));
    // when & then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/gyms" + searchFilter.getQueryString())
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-gym-list",
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
                    fieldWithPath("gyms[].levels[].color").description("레벨 색상코드"))));
  }

  @Test
  void getGymById() throws Exception {
    // given
    String id = "gymId";
    given(getGymUseCase.getById(id)).willReturn(GymTest.create());

    // when & then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/gyms/{id}", id)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-gym",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("암장 아이디")),
                responseFields(
                    fieldWithPath("id").description("암장 고유 아이디"),
                    fieldWithPath("name").description("암장명"),
                    fieldWithPath("address").description("암장 주소"),
                    fieldWithPath("latitude").description("위도"),
                    fieldWithPath("longitude").description("경도"),
                    fieldWithPath("tel").description("전화번호"),
                    fieldWithPath("image").description("로고 이미지"),
                    fieldWithPath("link").description("홈페이지 주소"),
                    fieldWithPath("updatedAt").description("업데이트 날짜"),
                    fieldWithPath("levels[]").description("암장 볼더링 레벨 목록"),
                    fieldWithPath("levels[].name").description("레벨 명"),
                    fieldWithPath("levels[].color").description("레벨 색상코드"))));
  }
}

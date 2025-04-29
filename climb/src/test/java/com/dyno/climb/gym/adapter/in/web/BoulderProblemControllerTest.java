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
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dyno.climb.gym.adapter.in.web.dto.request.BoulderProblemFilter;
import com.dyno.climb.gym.application.port.in.GetBoulderProblemUseCase;
import com.dyno.climb.gym.domain.BoulderProblem;
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
class BoulderProblemControllerTest {
  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private GetBoulderProblemUseCase getBoulderProblemUseCase;

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
  void getBoulderProblems() throws Exception {
    BoulderProblemFilter searchFilter = new BoulderProblemFilter("gymId", "keyword");
    // given
    given(getBoulderProblemUseCase.getByQuery(any())).willReturn(List.of(BoulderProblem.create()));

    // when & then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get(
                    "/api/boulder-problems" + searchFilter.getQueryString())
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-boulder-problem-list",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                queryParameters(
                    parameterWithName("gymId").description("gymId"),
                    parameterWithName("keyword").description("키워드").optional()),
                responseFields(
                    fieldWithPath("boulderProblems[]").description("볼더링 문제"),
                    fieldWithPath("boulderProblems[].id").description("볼더링 고유 아이디"),
                    fieldWithPath("boulderProblems[].sector").description("섹터명 - 연남"),
                    fieldWithPath("boulderProblems[].level.name").description("레벨명"),
                    fieldWithPath("boulderProblems[].level.color").description("레벨 색상 코드"),
                    fieldWithPath("boulderProblems[].image").description("이미지"),
                    fieldWithPath("boulderProblems[].gymId").description("Gym 고유 아이디"))));
  }

  @Test
  void getBoulderProblemById() throws Exception {
    // given
    String id = "boulderProblemId";
    given(getBoulderProblemUseCase.getById(id)).willReturn(BoulderProblem.create());

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/boulder-problems/{id}", id)
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-boulder-problem",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                pathParameters(parameterWithName("id").description("아이디")),
                responseFields(
                    fieldWithPath("id").description("볼더링 고유 아이디"),
                    fieldWithPath("sector").description("섹터명 - 연남"),
                    fieldWithPath("level.name").description("레벨명"),
                    fieldWithPath("level.color").description("레벨 색상 코드"),
                    fieldWithPath("image").description("이미지"),
                    fieldWithPath("gymId").description("Gym 고유 아이디"))));
  }
}

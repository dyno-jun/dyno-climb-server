package com.dyno.climb.record.adapter.in.web;

import static com.dyno.climb.config.ApiDocumentUtils.getDocumentRequest;
import static com.dyno.climb.config.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dyno.climb.record.adapter.in.web.dto.request.CreateHashtagRequest;
import com.dyno.climb.record.application.service.CreateHashtagUseCase;
import com.dyno.climb.record.application.service.GetHashtagUseCase;
import com.dyno.climb.record.domain.Hashtag;
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
class HashtagControllerTest {

  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private GetHashtagUseCase getHashtagUseCase;
  @MockBean private CreateHashtagUseCase createHashtagUseCase;

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
  void getHashtags() throws Exception {
    given(getHashtagUseCase.getTagsByKeyword(any())).willReturn(List.of(new Hashtag("id", "name")));
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/hashtags?" + "keyword=a")
                .header("Authorization", "Bearer some-auth-token")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-hashtag-list",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                queryParameters(parameterWithName("keyword").description("키워드").optional()),
                responseFields(
                    fieldWithPath("hashtags[]").description("해시태그 목록"),
                    fieldWithPath("hashtags[].id").description("해시태그 고유 아이디"),
                    fieldWithPath("hashtags[].name").description("해시태그 이름"))));
  }

  @Test
  void createHashtag() throws Exception {
    CreateHashtagRequest request = new CreateHashtagRequest("name");
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.post("/api/hashtags")
                .header("Authorization", "Bearer some-auth-token")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(
            document(
                "create-hashtag",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                requestFields(fieldWithPath("name").description("해시 태그 이름")),
                responseFields(fieldWithPath("id").description("해시 태그 아이디"))));
  }
}

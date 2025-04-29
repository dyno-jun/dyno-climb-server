package com.dyno.climb.user.adapter.in.web;

import static com.dyno.climb.config.ApiDocumentUtils.getDocumentRequest;
import static com.dyno.climb.config.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dyno.climb.config.WithCustomMockUser;
import com.dyno.climb.user.adapter.in.web.dto.request.UpdateUserDto;
import com.dyno.climb.user.application.port.in.LogoutUseCase;
import com.dyno.climb.user.application.port.in.UpdateUserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
class UserControllerTest {
  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockBean private UpdateUserUseCase updateUserUseCase;

  @MockBean private LogoutUseCase logoutUseCase;

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
  void logout() throws Exception {
    // when & then
    String authToken = "Bearer some-auth-token";

    this.mockMvc
        .perform(post("/api/users/me/logout").header("Authorization", authToken))
        .andExpect(status().isOk())
        .andDo(
            document(
                "logout",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication"))));
  }

  @Test
  @WithCustomMockUser
  void revoke() throws Exception {
    // when & then
    String authToken = "Bearer some-auth-token";

    this.mockMvc
        .perform(post("/api/users/me/revoke").header("Authorization", authToken))
        .andExpect(status().isOk())
        .andDo(
            document(
                "logout",
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication"))));
  }

  @Test
  @WithCustomMockUser
  void getUser() throws Exception {
    // when & then
    String authToken = "Bearer some-auth-token";

    this.mockMvc
        .perform(get("/api/users/me").header("Authorization", authToken))
        .andExpect(status().isOk())
        .andDo(
            document(
                "get-user",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                responseFields(
                    fieldWithPath("user.accountId").description("이메일 아이디"),
                    fieldWithPath("user.nickName").description("닉네임"),
                    fieldWithPath("user.startDate").description("시작일").optional(),
                    fieldWithPath("user.userLevel").description("시작일").optional(),
                    fieldWithPath("user.userLevel.currentExp").description("현재 경험치").optional(),
                    fieldWithPath("user.userLevel.levelUpExp").description("다음레벨업 경험치").optional(),
                    fieldWithPath("user.userLevel.level").description("레벨").optional(),
                    fieldWithPath("user.reach").description("팔길이").optional())));
  }

  @Test
  @WithCustomMockUser
  void updateUser() throws Exception {
    // when & then
    String authToken = "Bearer some-auth-token";

    UpdateUserDto request = new UpdateUserDto(LocalDate.now(), 1, "nickName");
    this.mockMvc
        .perform(
            put("/api/users/me")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andDo(
            document(
                "update-user",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("The authorization token used for authentication")),
                requestFields(
                    fieldWithPath("nickName").description("닉네임"),
                    fieldWithPath("startDate").type(String.class).description("시작일").optional(),
                    fieldWithPath("reach").type(Integer.class).description("팔길이").optional())));
  }
}

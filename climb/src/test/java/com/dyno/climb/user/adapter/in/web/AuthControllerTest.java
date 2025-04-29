package com.dyno.climb.user.adapter.in.web;

import static com.dyno.climb.config.ApiDocumentUtils.getDocumentRequest;
import static com.dyno.climb.config.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dyno.climb.user.adapter.in.web.dto.request.LoginUserDto;
import com.dyno.climb.user.adapter.in.web.dto.request.RegisterUserDto;
import com.dyno.climb.user.application.port.in.LoginUseCase;
import com.dyno.climb.user.application.port.in.RegisterUseCase;
import com.dyno.climb.user.domain.vo.Token;
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
class AuthControllerTest {
  private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private RegisterUseCase registerUseCase;

  @MockBean private LoginUseCase loginUseCase;

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
  void register() throws Exception {
    RegisterUserDto registerUserDto =
        new RegisterUserDto(LocalDate.now(), 2, "dyno.jin@gmail.com", "Password1234@", "DYNO");

    // when & then
    mockMvc
        .perform(
            post("/api/register")
                .content(objectMapper.writeValueAsString(registerUserDto))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(
            document(
                "register",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                    fieldWithPath("accountId").description("이메일 아이디"),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("nickName").description("닉네임"),
                    fieldWithPath("startDate").type(String.class).description("시작일").optional(),
                    fieldWithPath("reach").type(Integer.class).description("팔길이").optional())));
  }

  @Test
  void login() throws Exception {
    LoginUserDto loginUserDto = new LoginUserDto("dyno.jin@gmail.com", "Password1234@");
    given(loginUseCase.login(loginUserDto.getAccountId(), loginUserDto.getPassword()))
        .willReturn(new Token("", ""));

    // when & then
    mockMvc
        .perform(
            post("/api/login")
                .content(objectMapper.writeValueAsString(loginUserDto))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(
            document(
                "login",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                    fieldWithPath("accountId").description("이메일 아이디"),
                    fieldWithPath("password").description("비밀번호")),
                responseFields(
                    fieldWithPath("accessToken").description("접근 토큰"),
                    fieldWithPath("refreshToken").description("갱신 토큰"))));
  }
}

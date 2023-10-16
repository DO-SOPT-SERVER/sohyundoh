package org.sopt.secondSeminar.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.secondSeminar.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController target;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }

    @Test
    @DisplayName("멤버 생성 요청이 들어오면 location header에 uri가 담긴다.")
    @Transactional
    public void saveAndReturnHeader() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
        String request = "{\"name\": \"도소현\",\"nickname\":  \"소현쓰\",\"age\": 23,\"sopt\": {\"generation\": 33, \"part\": \"SERVER\"}}";
        String location = "api/member/" + String.valueOf(1L);
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .content(request)
                .contentType(textPlainUtf8));
        actions.andExpect(header().string("Location", location));
    }
}

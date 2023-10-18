package org.sopt.secondSeminar.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sopt.secondSeminar.Repository.MemberRepository;
import org.sopt.secondSeminar.domain.Member;
import org.sopt.secondSeminar.domain.SOPT;
import org.sopt.secondSeminar.domain.enums.Part;
import org.sopt.secondSeminar.dto.request.MemberCreateRequest;
import org.sopt.secondSeminar.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController target;

    @Mock
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

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
        String requestJson = "{\"name\": \"도소현\",\"nickname\":  \"소현쓰\",\"age\": 23,\"sopt\": {\"generation\": 33, \"part\": \"SERVER\"}}";
        long memberId = 1L; // 원하는 ID 값

        //create 메소드가 호출되면 memberId를 반환하도록 설정하였음 -> 실제 DB에는 질의하지 않기 때문에 memberService가 정상적으로 호출되면 memberId 호출할 것
        when(memberService.create(ArgumentMatchers.any(MemberCreateRequest.class))).thenReturn(String.valueOf(memberId));

        String location = "api/member/" + String.valueOf(memberId);
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                .content(requestJson)
                .contentType(textPlainUtf8));
        actions.andExpect(header().string("Location", location));
    }
}

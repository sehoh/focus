package com.example.focus.focussession;

import com.example.focus.focussession.dto.TagRequest;
import com.example.focus.focussession.service.FocusSessionServiceImpl;
import com.example.focus.focussession.service.FocusSessionTagServiceImpl;
import com.example.focus.member.MemberDto;
import com.example.focus.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TagApiController {

    private final FocusSessionServiceImpl focusSessionService;
    private final FocusSessionTagServiceImpl focusSessionTagService;
    private final MemberServiceImpl memberService;

    @PostMapping(value = "/api/tag")
    public ResponseEntity<Object> writeTag(@RequestBody TagRequest tagRequest,
                                           HttpSession loginSession) {
        List<String> tags = tagRequest.getTags();

        if (tags == null || tags.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "Tags cannot be empty"));
        }
//        String email = String.valueOf(loginSession.getAttribute("loginId"));
        String email = String.valueOf(tagRequest.getEmail());
        MemberDto memberDto = memberService.findMemberDtoByEmail(email);

        focusSessionTagService.createByTagRequest(tagRequest, memberDto);

        return ResponseEntity.status(200).body(Map.of("status", "created"));
    }
}

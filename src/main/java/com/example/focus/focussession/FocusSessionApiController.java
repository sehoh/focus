package com.example.focus.focussession;

import com.example.focus.member.MemberDto;
import com.example.focus.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FocusSessionApiController {

    private final FocusSessionServiceImpl focusSessionService;
    private final MemberServiceImpl memberService;


    @PostMapping(value = "/api/focussession/endclock")
    public ResponseEntity<Object> endClock(@RequestBody Map<String, String> payload,
                                           HttpSession session) {
        // TODO :: NULL check
        MemberDto member = memberService.findMember(String.valueOf(session.getAttribute("loginId"))).get().toDto();
        String startTime = String.valueOf(payload.get("startTime"));
        String endTime = String.valueOf(payload.get("endTime"));

        LocalDateTime startDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);
        FocusSessionDto focusSession = FocusSessionDto.builder()
                .member(member)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
        focusSessionService.save(focusSession.toEntity());

        return ResponseEntity.ok().build();
    }

}

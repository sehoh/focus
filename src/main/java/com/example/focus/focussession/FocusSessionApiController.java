package com.example.focus.focussession;

import com.example.focus.DateTimeUtils;
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


    @PostMapping(value = "/api/focus-session/endclock")
    public ResponseEntity<Object> endClock(@RequestBody Map<String, String> payload,
                                           HttpSession loginSession) {
        // TODO :: NULL check
        MemberDto member;
        try {
            member = memberService.findMember(String.valueOf(loginSession.getAttribute("loginId"))).get().toDto();
            String startTime = String.valueOf(payload.get("startTime"));
            String endTime = String.valueOf(payload.get("endTime"));

            LocalDateTime startDateTimeKst = DateTimeUtils.parseIsoStringToKst(startTime);
            LocalDateTime endDateTimeKst = DateTimeUtils.parseIsoStringToKst(endTime);

            FocusSessionDto focusSession = FocusSessionDto.builder()
                    .member(member)
                    .startDateTime(startDateTimeKst)
                    .endDateTime(endDateTimeKst)
                    .build();
            focusSessionService.save(focusSession.toEntity());

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/api/focus-session/start")
    public ResponseEntity<Object> startClock(HttpSession loginSession) {
        // FocusStatus 변경

        //
        return ResponseEntity.ok().build();
    }
}

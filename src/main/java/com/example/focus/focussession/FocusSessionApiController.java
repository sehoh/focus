package com.example.focus.focussession;

import com.example.focus.DateTimeUtils;
import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.dto.FocusSessionDto;
import com.example.focus.focussession.service.FocusSessionService;
import com.example.focus.focussession.service.FocusSessionServiceImpl;
import com.example.focus.member.Member;
import com.example.focus.member.MemberDto;
import com.example.focus.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

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
        try {
            // 단순조회이므로 entity로 가져옴
            Member member = memberService.findMemberByEmail(String.valueOf(loginSession.getAttribute("loginId"))).get();


            // TODO : refactor
            String startTime = String.valueOf(payload.get("startTime"));
            String endTime = String.valueOf(payload.get("endTime"));

            LocalDateTime startDateTimeKst = DateTimeUtils.parseIsoStringToKst(startTime);
            LocalDateTime endDateTimeKst = DateTimeUtils.parseIsoStringToKst(endTime);

            // Service에서 focusSessionDto -> entity를 받아서 생성
//            FocusSessionDto focusSession = FocusSessionDto.builder()
//                    .member(member)
//                    .startDateTime(startDateTimeKst)
//                    .endDateTime(endDateTimeKst)
//                    .build();
//            focusSessionService.create(focusSession.toEntity());

            // FocusSession entity에는 fk로 member_id가 들어감!
            focusSessionService.create(
                    FocusSession.builder()
                            .startDateTime(startDateTimeKst)
                            .endDateTime(endDateTimeKst)
                            .member(member).build());

        } catch (NoSuchElementException e){
            return ResponseEntity.status(404).body(Map.of("error", "Member Not Found"));
        } catch (Exception e){
            return ResponseEntity.status(400).body(Map.of("status", "badRequest"));
        }
        return ResponseEntity.status(201).body(Map.of("status", "created"));
    }

    @PostMapping(value = "/api/focus-session/start")
    public ResponseEntity<Object> startClock(HttpSession loginSession) {
        // FocusStatus 변경

        //
        return ResponseEntity.ok().build();
    }
}

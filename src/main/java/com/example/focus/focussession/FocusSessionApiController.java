package com.example.focus.focussession;

import com.example.focus.DateTimeUtils;
import com.example.focus.focussession.domain.FocusSession;
import com.example.focus.focussession.dto.*;
import com.example.focus.focussession.service.FocusSessionServiceImpl;
import com.example.focus.member.MemberDto;
import com.example.focus.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.example.focus.member.FocusStatus.*;
import static com.example.focus.member.FocusStatus.FOCUS;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FocusSessionApiController {

    private final FocusSessionServiceImpl focusSessionService;
    private final MemberServiceImpl memberService;


    @PostMapping(value = "/api/focus-session/endclock")
    public ResponseEntity<Object> endClock(@RequestBody Map<String, String> payload,
                                           HttpSession loginSession) {
        try {
//            String email = String.valueOf(loginSession.getAttribute("loginId"));

            String startTime = String.valueOf(payload.get("startTime"));
            String endTime = String.valueOf(payload.get("endTime"));
            String email = String.valueOf(payload.get("email"));
            MemberDto memberDto = memberService.findMemberDtoByEmail(email);

            LocalDateTime startDateTimeKst = DateTimeUtils.parseIsoStringToKst(startTime);
            LocalDateTime endDateTimeKst = DateTimeUtils.parseIsoStringToKst(endTime);


            focusSessionService.create(
                    FocusSession.builder()
                            .startDateTime(startDateTimeKst)
                            .endDateTime(endDateTimeKst)
                            .member(memberDto.toEntity()).build());

            memberService.updateFocusStatus(email, NONE);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(Map.of("error", "Member Not Found"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("status", "badRequest"));
        }
        return ResponseEntity.status(201).body(Map.of("status", "created"));
    }

    @PostMapping(value = "/api/focus-session/start")
    public ResponseEntity<Object> startClock(@RequestBody EmailInfo emailInfo) {
        String email = emailInfo.getEmail();
        memberService.updateFocusStatus(email, FOCUS);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/focus-session/days/{email}")
    public ResponseEntity<List<DailyCumulativeTime>> myPageDailyStatistics(@PathVariable("email") String email) {
        MemberDto memberDto = memberService.findMemberDtoByEmail(email);
        List<DailyCumulativeTime> cumulativeTimes = focusSessionService.findDailyCumulativeTimeByMemberId(memberDto.getId());

        return ResponseEntity.ok(cumulativeTimes);
    }

    @GetMapping(value = "/api/focus-session/weeks/{email}")
    public ResponseEntity<List<WeeklyCumulativeTime>> myPageWeekStatistics(@PathVariable("email") String email) {
        MemberDto memberDto = memberService.findMemberDtoByEmail(email);
        List<WeeklyCumulativeTime> cumulativeWeekTimes = focusSessionService.findWeeklyCumulativeTimeByMemberId(memberDto.getId());

        return ResponseEntity.ok(cumulativeWeekTimes);
    }

    @GetMapping(value = "/api/focus-session/months/{email}")
    public ResponseEntity<List<MonthlyCumulativeTime>> mypageMonthlyStatistics(@PathVariable("email") String email) {
        MemberDto memberDto = memberService.findMemberDtoByEmail(email);
        List<MonthlyCumulativeTime> monthlyCumulativeTimes = focusSessionService.findMonthlyCumulativeTimeByMemberId(memberDto.getId());

        return ResponseEntity.ok(monthlyCumulativeTimes);
    }

    @GetMapping("/api/focus-session/search")
    public List<FocusSessionResponse> searchByTag(@RequestParam String tag) {

        return focusSessionService.findFocusSessionByTagName(tag).stream().map(FocusSessionDto::toResponse).toList();
    }

    @GetMapping("/api/focus-session/search/multi")
    public ResponseEntity<List<FocusSessionResponse>> searchByTags(@RequestParam List<String> tags) {

        List<FocusSessionResponse> focusSessionResponses = focusSessionService.findFocusSessionByTagNames(tags)
                .stream().map(FocusSessionDto::toResponse).toList();

        return ResponseEntity.ok(focusSessionResponses);
    }
}

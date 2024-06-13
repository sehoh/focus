package com.example.focus;

import com.example.focus.focussession.dto.FocusSessionDto;
import com.example.focus.member.MemberDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DateTimeUtils {
    public static LocalDateTime nowFromZone() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    public static LocalDateTime parseIsoStringToKst(String isoStringDate) {
        LocalDateTime utcDateTime = LocalDateTime.parse(isoStringDate, DateTimeFormatter.ISO_DATE_TIME);
        return utcDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    public static FocusSessionDto getTimeInfoFromPayLoad(Map<String, String> payload, MemberDto memberDto) {
        String startTime = String.valueOf(payload.get("startTime"));
        String endTime = String.valueOf(payload.get("endTime"));

        LocalDateTime startDateTimeKst = DateTimeUtils.parseIsoStringToKst(startTime);
        LocalDateTime endDateTimeKst = DateTimeUtils.parseIsoStringToKst(endTime);
        return FocusSessionDto.builder()
                .startDateTime(startDateTimeKst)
                .endDateTime(endDateTimeKst)
                .member(memberDto)
                .build();
    }
}

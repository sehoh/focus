package com.example.focus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static LocalDateTime nowFromZone() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    public static LocalDateTime parseIsoStringToKst(String isoStringDate) {
        LocalDateTime utcDateTime = LocalDateTime.parse(isoStringDate, DateTimeFormatter.ISO_DATE_TIME);
        return utcDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }
}

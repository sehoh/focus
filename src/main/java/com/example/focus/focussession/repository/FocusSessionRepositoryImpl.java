package com.example.focus.focussession.repository;

import com.example.focus.focussession.dto.DailyCumulativeTime;
import com.example.focus.focussession.dto.MonthlyCumulativeTime;
import com.example.focus.focussession.dto.WeeklyCumulativeTime;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FocusSessionRepositoryImpl implements FocusSessionRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<DailyCumulativeTime> findCumulativeTimeByDate() {
        String sql = "SELECT " +
                "DATE(start_date_time) as date, " +
                "SUM(TIMESTAMPDIFF(SECOND, start_date_time, end_date_time)) as cumulative_time_diff " +
                "FROM focus_session " +
                "GROUP BY DATE(start_date_time) " +
                "ORDER BY DATE(start_date_time) DESC";

        return jdbcTemplate.query(sql, new CumulativeTimeRowMapper());
    }

    @Override
    public List<DailyCumulativeTime> findCumulativeTimeByDateAndMemberId(Long memberId) {
        String sql = "SELECT " +
                "DATE(start_date_time) as date, " +
                "SUM(TIMESTAMPDIFF(SECOND, start_date_time, end_date_time)) as cumulative_time_diff " +
                "FROM focus_session " +
                "WHERE member_id = ? " +
                "GROUP BY DATE(start_date_time) " +
                "ORDER BY DATE(start_date_time) DESC";

        return jdbcTemplate.query(sql, new Object[]{memberId}, new CumulativeTimeRowMapper());
    }

    private static class CumulativeTimeRowMapper implements RowMapper<DailyCumulativeTime> {
        @Override
        public DailyCumulativeTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            LocalDate date = rs.getDate("date").toLocalDate();
            Long cumulativeTimeDiff = rs.getLong("cumulative_time_diff");

            return new DailyCumulativeTime(date, cumulativeTimeDiff);
        }
    }

    @Override
    public List<WeeklyCumulativeTime> findCumulativeTimeByWeekAndMemberId(Long memberId) {
        String sql = "WITH RECURSIVE weeks AS (" +
                "    SELECT " +
                "        DATE(CONCAT(YEAR(CURDATE()), '-01-01')) AS start_of_week, " +
                "        DATE_ADD(DATE(CONCAT(YEAR(CURDATE()), '-01-01')), INTERVAL (7 - WEEKDAY(DATE(CONCAT(YEAR(CURDATE()), '-01-01')))) DAY) AS end_of_week " +
                "    UNION ALL " +
                "    SELECT " +
                "        start_of_week + INTERVAL 1 WEEK, " +
                "        end_of_week + INTERVAL 1 WEEK " +
                "    FROM weeks " +
                "    WHERE end_of_week < CURDATE() " +
                ") " +
                "SELECT " +
                "    w.start_of_week AS week_start, " +
                "    w.end_of_week AS week_end, " +
                "    IFNULL(SUM(TIMESTAMPDIFF(SECOND, fs.start_date_time, fs.end_date_time)), 0) AS cumulative_time_diff " +
                "FROM weeks w " +
                "LEFT JOIN focus_session fs " +
                "ON fs.start_date_time >= w.start_of_week " +
                "AND fs.start_date_time < w.end_of_week " +
                "AND fs.member_id = ? " + // 매개변수로 전달된 memberId 사용
                "GROUP BY w.start_of_week, w.end_of_week " +
                "ORDER BY w.start_of_week DESC";

        return jdbcTemplate.query(sql, new Object[]{memberId}, new CumulativeWeekTimeRowMapper());
    }

    private static class CumulativeWeekTimeRowMapper implements RowMapper<WeeklyCumulativeTime> {
        @Override
        public WeeklyCumulativeTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            LocalDate weekStart = rs.getDate("week_start").toLocalDate();
            LocalDate weekEnd = rs.getDate("week_end").toLocalDate();
            Long cumulativeTimeDiff = rs.getLong("cumulative_time_diff");

            return new WeeklyCumulativeTime(weekStart, weekEnd, cumulativeTimeDiff);
        }
    }

    @Override
    public List<MonthlyCumulativeTime> findCumulativeTimeByMonthAndMemberId(Long memberId) {
        String sql = "WITH RECURSIVE months AS (" +
                "    SELECT " +
                "        DATE(CONCAT(YEAR(CURDATE()), '-01-01')) AS start_of_month, " +
                "        LAST_DAY(DATE(CONCAT(YEAR(CURDATE()), '-01-01'))) AS end_of_month " +
                "    UNION ALL " +
                "    SELECT " +
                "        DATE_ADD(start_of_month, INTERVAL 1 MONTH), " +
                "        LAST_DAY(DATE_ADD(start_of_month, INTERVAL 1 MONTH)) " +
                "    FROM months " +
                "    WHERE start_of_month < DATE(CONCAT(YEAR(CURDATE()), '-', LPAD(MONTH(CURDATE()), 2, '0'), '-01')) " +
                ") " +
                "SELECT " +
                "    m.start_of_month AS month_start, " +
                "    m.end_of_month AS month_end, " +
                "    IFNULL(SUM(TIMESTAMPDIFF(SECOND, fs.start_date_time, fs.end_date_time)), 0) AS cumulative_time_diff " +
                "FROM months m " +
                "LEFT JOIN focus_session fs " +
                "ON fs.start_date_time >= m.start_of_month " +
                "AND fs.start_date_time < DATE_ADD(m.end_of_month, INTERVAL 1 DAY) " +
                "AND fs.member_id = ? " +
                "GROUP BY m.start_of_month, m.end_of_month " +
                "ORDER BY m.start_of_month DESC";

        return jdbcTemplate.query(sql, new Object[]{memberId}, new CumulativeMonthTimeRowMapper());
    }

    private static class CumulativeMonthTimeRowMapper implements RowMapper<MonthlyCumulativeTime> {
        @Override
        public MonthlyCumulativeTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            LocalDate monthStart = rs.getDate("month_start").toLocalDate();
            LocalDate monthEnd = rs.getDate("month_end").toLocalDate();
            Long cumulativeTimeDiff = rs.getLong("cumulative_time_diff");

            return new MonthlyCumulativeTime(monthStart, monthEnd, cumulativeTimeDiff);
        }
    }
}

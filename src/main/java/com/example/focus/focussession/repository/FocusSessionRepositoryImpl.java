package com.example.focus.focussession.repository;

import com.example.focus.focussession.dto.CumulativeTimeDto;
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
    public List<CumulativeTimeDto> findCumulativeTimeByDate() {
        String sql = "SELECT " +
                "DATE(start_date_time) as date, " +
                "SUM(TIMESTAMPDIFF(SECOND, start_date_time, end_date_time)) as cumulative_time_diff " +
                "FROM focus_session " +
                "GROUP BY DATE(start_date_time) " +
                "ORDER BY DATE(start_date_time) DESC";

        return jdbcTemplate.query(sql, new CumulativeTimeRowMapper());
    }

    @Override
    public List<CumulativeTimeDto> findCumulativeTimeByDateAndMemberId(Long memberId) {
        String sql = "SELECT " +
                "DATE(start_date_time) as date, " +
                "SUM(TIMESTAMPDIFF(SECOND, start_date_time, end_date_time)) as cumulative_time_diff " +
                "FROM focus_session " +
                "WHERE member_id = ? " +
                "GROUP BY DATE(start_date_time) " +
                "ORDER BY DATE(start_date_time) DESC";

        return jdbcTemplate.query(sql, new Object[]{memberId}, new CumulativeTimeRowMapper());
    }

    private static class CumulativeTimeRowMapper implements RowMapper<CumulativeTimeDto> {
        @Override
        public CumulativeTimeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            LocalDate date = rs.getDate("date").toLocalDate();
            Long cumulativeTimeDiff = rs.getLong("cumulative_time_diff");

            return new CumulativeTimeDto(date, cumulativeTimeDiff);
        }
    }
}

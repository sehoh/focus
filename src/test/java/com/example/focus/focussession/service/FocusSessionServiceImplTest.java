package com.example.focus.focussession.service;

import com.example.focus.focussession.dto.DailyCumulativeTime;
import com.example.focus.focussession.dto.WeeklyCumulativeTime;
import com.example.focus.focussession.repository.FocusSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class FocusSessionServiceImplTest {
//    @Autowired
    private FocusSessionService focusSessionService;
    @Autowired
    private FocusSessionRepository focusSessionRepository;

    @BeforeEach
    public void beforeEach() {
//        focusSessionService.findFocusSessionByMemberId()

    }

    @Test
    void cumulativeTimeByDateTest() {
//        FocusSessionServiceImpl focusSessionService = new FocusSessionServiceImpl(focusSessionRepository);

        List<DailyCumulativeTime> rs = focusSessionRepository.findCumulativeTimeByDate();

        //
        assertThat(rs).isNotEmpty();
        System.out.println("rs = " + rs);
        assertThat(rs.get(0).getDate()).isNotNull();
        assertThat(rs.get(0).getCumulativeTimeDiff()).isGreaterThan(0L);

    }
    // TODO : 테스트 코드 어떻게?
    @Test
    void cumulativeWeekTimeByWeekTest() {
        Long memberId = 1L;
        List<WeeklyCumulativeTime> rs = focusSessionRepository.findCumulativeTimeByWeekAndMemberId(memberId);

        assertThat(rs).isNotEmpty();

        WeeklyCumulativeTime thisWeek = rs.get(0);



    }
}
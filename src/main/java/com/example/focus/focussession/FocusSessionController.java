package com.example.focus.focussession;

import com.example.focus.focussession.service.FocusSessionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FocusSessionController {
    private final FocusSessionServiceImpl focusSessionService;

    @GetMapping(value = "/focussession/stopwatch")
    public void stopwatch() {
    }

    // 게스트용
    @GetMapping(value = "/focus-session/tag-popup")
    public String showTagPopup() {
        return "focussession/tag-popup";
    }

//    @GetMapping(value = "/focus-session/tag-popup/{memberId}")
//    public
}

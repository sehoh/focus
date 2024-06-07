package com.example.focus.focussession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FocusSessionController {
    private final FocusSessionServiceImpl focusSessionService;

    @GetMapping(value = "/focussession/stopwatch")
    public void stopwatch() {
    }

}

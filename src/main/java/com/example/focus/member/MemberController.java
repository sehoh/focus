package com.example.focus.member;

import com.example.focus.focussession.dto.DailyCumulativeTime;
import com.example.focus.focussession.dto.MemberListDto;
import com.example.focus.focussession.service.FocusSessionServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;
    private final FocusSessionServiceImpl focusSessionService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        memberService.join(memberDto.toEntity());
        return "redirect:/";
    }

    @GetMapping(value = "/members/login")
    public void loginForm() {

    }

    @PostMapping(value = "/members/login")
    public String login(String email, String pwd, HttpSession session) {
        String path = "members/login";
        try {
            MemberDto member = memberService.findMemberDtoByEmail(email);
            if (member.getPwd().equals(pwd)) {
                session.setAttribute("loginId", email);
                path = "home";
            }
        } catch (NoSuchElementException e) {
            return path;
        }
        return path;
    }

    @PostMapping(value = "/members/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String focusList(Model model) {
        List<MemberListDto> focusMembers = memberService.getMembersDailyStat();
        model.addAttribute("focusMembers", focusMembers);
        return "members/memberList";
    }

    @GetMapping(value = "/members/mypage")
    public ModelAndView myPageStatistics(@RequestParam String email) {
        MemberDto memberDto = memberService.findMemberDtoByEmail(email);
        List<DailyCumulativeTime> cumulativeTimes = focusSessionService.findCumulativeTimeByDateAndMemberId(memberDto.getId());

        ModelAndView mav = new ModelAndView("members/mypage");
        mav.addObject("cumulativeTimes", cumulativeTimes);
        return mav;
    }
}

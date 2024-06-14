package com.example.focus.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;

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
            MemberDto member = memberService.findMemberByEmail(email).get().toDto();
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
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


    @GetMapping(value = "/members/mypage")
    public ModelAndView Mypage(String email, HttpSession loginSession) {
//        MemberDto memberDto = memberService.findMember(String.valueOf(loginSession.getAttribute("loginId"))).get().toDto();
        MemberDto memberDto = memberService.findMemberByEmail(email).get().toDto();
        ModelAndView mav = new ModelAndView("members/detail");
        mav.addObject("member", memberDto);
        return mav;
    }
}

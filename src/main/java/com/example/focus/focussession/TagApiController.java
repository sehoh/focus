package com.example.focus.focussession;

import com.example.focus.focussession.dto.TagDto;
import com.example.focus.focussession.dto.TagRequest;
import com.example.focus.focussession.service.FocusSessionServiceImpl;
import com.example.focus.focussession.service.FocusSessionTagServiceImpl;
import com.example.focus.member.MemberDto;
import com.example.focus.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.focus.member.FocusStatus.NONE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TagApiController {

    private final FocusSessionServiceImpl focusSessionService;
    private final FocusSessionTagServiceImpl focusSessionTagService;
    private final MemberServiceImpl memberService;

    @PostMapping(value = "/api/tag")
    public ResponseEntity<Object> writeTag(@RequestBody TagRequest tagRequest) {
        List<String> tags = tagRequest.getTags();

        if (tags == null || tags.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "Tags cannot be empty"));
        }
        String email = String.valueOf(tagRequest.getEmail());
        MemberDto memberDto = memberService.findMemberDtoByEmail(email);

        focusSessionTagService.createByTagRequest(tagRequest, memberDto);

        memberService.updateFocusStatus(email, NONE);

        return ResponseEntity.status(200).body(Map.of("status", "created"));
    }

    @GetMapping(value = "/api/tag/{email}")
    public ResponseEntity<Object> getTags(@PathVariable("email") String email) {
        List<TagDto> tags = focusSessionTagService.findTagsByEmail(email);
        return ResponseEntity.ok(tags);
    }
}

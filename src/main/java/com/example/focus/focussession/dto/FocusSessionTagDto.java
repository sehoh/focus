package com.example.focus.focussession.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FocusSessionTagDto {
    private Long id;
    private FocusSessionDto focusSessionDto;
    private TagDto tagDto;
}

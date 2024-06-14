package com.example.focus.focussession.dto;

import com.example.focus.focussession.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {
    private Long id;
    private String name;
    private LocalDate created;

    public Tag toEntity() {
        return Tag.builder()
                .id(this.id)
                .name(this.name)
                .created(this.created).build();
    }
}

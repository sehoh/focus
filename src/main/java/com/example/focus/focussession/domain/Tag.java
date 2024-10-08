package com.example.focus.focussession.domain;

import com.example.focus.focussession.dto.TagDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Tag implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @CreatedDate
    private LocalDate created;

    @Override
    public boolean isNew() {
        return created == null;
    }

    public TagDto toDto() {
        return TagDto.builder()
                .id(this.id)
                .name(this.name)
                .created(this.created).build();
    }

}

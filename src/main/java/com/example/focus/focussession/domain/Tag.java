package com.example.focus.focussession.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    @CreatedDate
    private LocalDate created;

    @Override
    public boolean isNew() {
        return created == null;
    }

    @Builder
    public Tag(String name, LocalDate created) {
        this.name = name;
        this.created = created;
    }
}

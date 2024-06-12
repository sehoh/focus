package com.example.focus.focussession.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FocusSessionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "focussession_id")
    private FocusSession focusSession;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}

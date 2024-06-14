package com.example.focus.focussession.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FocusSessionTag { // FocusSession과 Tag의 다대다 관계 매핑을 위한 연결 클래스
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

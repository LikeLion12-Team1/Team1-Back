package com.magnetic.domain.crew.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.

public class CrewPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crew_post_id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="crew_id")
    private Crew crew;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;
}

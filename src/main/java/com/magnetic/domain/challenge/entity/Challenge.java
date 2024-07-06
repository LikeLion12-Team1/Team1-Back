package com.magnetic.domain.challenge.entity;

import com.magnetic.domain.user.entity.UserChallenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long challengeId;

    private String region;
    private String sportsCategory;
    private String notice;
    private String name;

    private LocalDate startAt;
    private LocalDate untilWhen;
}

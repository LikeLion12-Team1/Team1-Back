package com.magnetic.domain.user.entity;

import com.magnetic.domain.challenge.entity.Challenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_challenge_id")
    private Long userChallengeId;

    private String status;

    @Column(name = "verification_count", columnDefinition = "BIGINT default 0")
    private Long verificationCount;

    @Column(name = "is_paid_up", columnDefinition = "TINYINT default 0")
    private Byte isPaidUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    public void verify() {
        verificationCount++;
    }

    public void paidUp() {
        isPaidUp = 1;
    }
}

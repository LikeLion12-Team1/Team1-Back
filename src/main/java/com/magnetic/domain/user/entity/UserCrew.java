package com.magnetic.domain.user.entity;

import com.magnetic.domain.crew.entity.Crew;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCrew extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_crew_id")
    private Long userCrewId;

    @Column(name = "status", nullable = false, columnDefinition = "varchar(10) default 'active'")
    private String status;

    private LocalDate inactiveDate;

    private LocalDate joinedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;
}

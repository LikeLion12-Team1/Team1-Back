package com.magnetic.domain.crew.entity;

import com.magnetic.domain.user.entity.UserCrew;
import com.magnetic.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crew extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long crewId;

    private String name;
    private String region;
    private String sportsCategory;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
    private List<UserCrew> userCrewList = new ArrayList<>();
}

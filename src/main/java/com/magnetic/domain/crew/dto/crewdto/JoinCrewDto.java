package com.magnetic.domain.crew.dto.crewdto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter
@Setter

public class JoinCrewDto {

    private Long userId;

    private Long crewId;

    private Long crewCount;

    private LocalDate joinedAt;

    @Builder
    public JoinCrewDto(Long userId, Long crewId, Long crewCount, LocalDate joinedAt) {
        this.userId = userId;
        this.crewId= crewId;
        this.crewCount = crewCount;
        this.joinedAt = joinedAt;
    }

//    //JoinCrewRequestDto 객체를 Crew 객체로 변환
//    //Post 객체는 실제 데이터베이스에 저장될 Post 엔티티
//    public Crew toEntity() {
//        return Crew.builder()
//                .userId(userId)
//                .crewId(crewId)
//                .joinedAt(joinedAt)
//                .build();
//    }


}

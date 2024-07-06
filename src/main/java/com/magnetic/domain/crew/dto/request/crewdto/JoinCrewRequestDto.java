package com.magnetic.domain.crew.dto.request.crewdto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@Getter

public class JoinCrewRequestDto {

    public Long userId;

    public Long crewId;

    @Builder
    public JoinCrewRequestDto(Long userId, Long crewId){
        this.userId = userId;
        this.crewId = crewId;
    }
}

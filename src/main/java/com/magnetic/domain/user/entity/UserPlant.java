package com.magnetic.domain.user.entity;

import com.magnetic.domain.plant.entity.Plant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_plant_id")
    private Long userPlantId;

    private Byte isLocked;
    private LocalDate getAt;
    private Byte isMain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    public void unlock() {
        isLocked = 1;
    }

    public void setMain() {
        isMain = 1;
    }

    public void undoMain() {
        isMain = 0;
    }
}

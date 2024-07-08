package com.magnetic.domain.plant.entity;

import com.magnetic.domain.user.entity.UserPlant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Long plantId;

    private String type;
    private String name;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<UserPlant> userPlantList = new ArrayList<>();
}

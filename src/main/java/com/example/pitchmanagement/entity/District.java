package com.example.pitchmanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "district")
public class District {

    @Id
    @Column(name = "district_id")
    private String districtId;

    @Column(name = "district_name")
    private String districtName;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ward> wards = new HashSet<>();
}

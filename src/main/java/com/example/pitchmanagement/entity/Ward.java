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
@Table(name = "ward")
public class Ward {

    @Id
    @Column(name = "ward_id")
    private String wardId;

//    @Column(name = "DistrictID")
//    private String districtId;

    @Column(name = "ward_name")
    private String wardName;

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
}

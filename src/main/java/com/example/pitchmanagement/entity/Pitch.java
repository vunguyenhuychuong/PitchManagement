package com.example.pitchmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "pitch")
public class Pitch {
    @Id
    @Column(name = "pitch_id")
    private String pitchID;

//    @Column(name = "ward_id")
//    private String wardID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", nullable = false)
    private Ward ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

//    @Column(name = "district_id")
//    private String districtID;

    @Column(name = "member_id")
    private String memberID;

    @Column(name = "pitch_name")
    private String pitchName;

    @Column(name = "pitch_address")
    private String pitchAddress;

    @Column(name = "estimation")
    private int estimation;

    @Column(name = "pitch_description")
    private String pitchDesc;

    @Column(name = "pitch_status")
    private boolean status;




}

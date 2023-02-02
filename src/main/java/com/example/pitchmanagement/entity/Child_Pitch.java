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
@Table(name = "children_pitch")
public class Child_Pitch {
    @Id
    @Column(name = "children_pitch_id")
    private String childrenPitchID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pitch_id", nullable = false)
    private Pitch pitchID;

    @Column (name = "children_pitch_name")
    private String childrenPitchName;

    @Column (name = "children_pitch_type")
    private String childrenPitchType;

    @Column (name = "price")
    private double price;

    @Column (name = "status_children_pitch")
    private boolean childrenPitchStatus;
}

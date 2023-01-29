package com.example.pitchmanagement.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class User {

    @Id
    @Column(name = "member_id")
    private String memberId;

//    @Column(name = "RoleID")
//    private String roleId;

//    @Column(name = "WardID")
//    private String wardId;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "member_address")
    private String memberAddress;

    @Column(name = "email")
    private String email;

    @Column(name = "img_link")
    private String imgLink;

    @Column(name = "owner_status")
    private boolean ownerStatus;

    @Column(name = "member_status")
    private boolean memberStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @Override
    public String toString() {
        return "User{" +
                "memberId='" + memberId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", email='" + email + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", ownerStatus=" + ownerStatus +
                ", memberStatus=" + memberStatus +
                ", role=" + role +
                ", ward=" + ward +
                '}';
    }
}

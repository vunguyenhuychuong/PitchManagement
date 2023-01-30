package com.example.pitchmanagement.repository;

import com.example.pitchmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByPassword(String password);

//    @Query(value = "SELECT w FROM user_name w WHERE w.district.districtId = ?1")

    User findByUserName(String userName);
}

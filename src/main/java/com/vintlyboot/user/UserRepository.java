package com.vintlyboot.user;

import com.vintlyboot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // ID 중복 확인
    int countByUserId(String memberId);

    // email 중복 확인
    int countByEmail(String email);
}

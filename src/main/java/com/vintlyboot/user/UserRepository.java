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

    // 닉네임 중복 체크
    int countByNickname(String nickname);

    // 회원가입
    @Override
    <S extends UserEntity> S save(S entity);
}

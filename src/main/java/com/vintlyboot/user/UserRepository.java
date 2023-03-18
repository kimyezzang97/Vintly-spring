package com.vintlyboot.user;

import com.vintlyboot.entities.User;
import com.vintlyboot.user.model.ReqJoinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ID 중복 확인
    int countByUserId(String memberId);

    // email 중복 확인
    int countByEmail(String email);

    // 닉네임 중복 체크
    int countByNickname(String nickname);


}

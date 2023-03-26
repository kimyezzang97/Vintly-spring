package com.vintlyboot.user;

import com.vintlyboot.entities.User;
import com.vintlyboot.user.model.ReqJoinDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ID 중복 확인
    int countByUserId(String memberId);

    // email 중복 확인
    int countByEmail(String email);

    // 닉네임 중복 체크
    int countByNickname(String nickname);

    // 아이디, 인증코드 체크
    int countByUserIdAndEmailCode(String userId, String emailCode);

    // userId로 User엔티티 가져오기
    User findByUserIdAndEmailCode(String userId, String EmailCode);

    // 인증기간 지난 ID 삭제
    int deleteByEmailExDateBeforeAndUseYn(Timestamp today, String useYn);

}

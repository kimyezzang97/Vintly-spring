package com.vintlyboot.user;

import com.vintlyboot.common.CustomResponse;
import com.vintlyboot.entities.User;
import com.vintlyboot.user.model.ReqJoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 중복 아이디 체크
    public int getChkId(String id) {
            return userRepository.countByUserId(id);
        }

    // email 중복 체크
    public int getChkEmail(String email){
        return userRepository.countByEmail(email);
    }

    // 닉네임 중복 체크
    public int getChkNickname(String nickname){
        return userRepository.countByNickname(nickname);
    }

    // 회원가입 처리
    public ResponseEntity<?> createUser(ReqJoinDTO reqJoinDTO){
        if(0!=getChkId(reqJoinDTO.getUserId()) || 0!=getChkEmail(reqJoinDTO.getEmail()) || 0!=getChkNickname(reqJoinDTO.getNickname())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(CustomResponse.builder()
                            .ok(false)
                            .statusCode(HttpStatus.CONFLICT.value())
                            .message("중복확인을 다시 해주세요")
                            .build() );
        }
        // 비밀번호 암호화
        String encodePassword = bCryptPasswordEncoder.encode(reqJoinDTO.getUserPw());
        reqJoinDTO.setUserPw(encodePassword);
        String id = userRepository.save(reqJoinDTO.toEntity()).getUserId();

        return ResponseEntity.status(HttpStatus.OK).body(CustomResponse.builder()
                .ok(true).statusCode(HttpStatus.OK.value()).message(id + " ID로 회원가입을 성공하였습니다.").build());
    }

}
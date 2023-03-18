package com.vintlyboot.user;

import com.vintlyboot.user.model.ReqJoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
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

        return null;
    }

    // 회원가입 ID or email 중복 체크(중복+정규식)
    public boolean chkIdJoin(String id){
        boolean chk = true;
        if(0!=getChkId(id)) chk = false;

        // 정규식 체크
        String idRegx = "/^[a-z]+[a-z0-9]{4,20}$/g";
        if(idRegx.matches(id)!=true) chk = false;

    return chk;
    }
}
package com.vintlyboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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

    }



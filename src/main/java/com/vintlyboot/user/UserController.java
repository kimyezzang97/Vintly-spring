package com.vintlyboot.user;

import com.vintlyboot.user.model.ReqJoinDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // ID 중복 확인
    @GetMapping("/id/{id}")
    public int getChkId(@PathVariable("id") String id){
        return userService.getChkId(id);
    }

    // email 중복 확인
    @GetMapping("/email/{email}")
    public int getChkEmail(@PathVariable("email") String email){
        return userService.getChkEmail(email);
    }

    // nickname 중복 확인
    @GetMapping("/nickname/{nickname}")
    public int getChkNickname(@PathVariable("nickname") String nickname){
        return userService.getChkNickname(nickname);
    }

    // 회원가입
    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody ReqJoinDTO reqJoinDTO) throws MessagingException, IOException{
        return userService.createUser(reqJoinDTO);
    }

    // 회원가입 인증
    @GetMapping("/enable")
    public ResponseEntity<?> enableUser(@RequestParam String id, @RequestParam String code)  {

        return userService.enableUser(id, code);
    }



}

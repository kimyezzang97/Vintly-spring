package com.vintlyboot.user;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

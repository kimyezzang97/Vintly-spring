package com.vintlyboot.user;

import com.vintlyboot.common.CustomResponse;
import com.vintlyboot.entities.User;
import com.vintlyboot.user.model.ReqJoinDTO;
import com.vintlyboot.util.MailService;
import com.vintlyboot.util.model.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@EnableScheduling
public class UserService {
    private UserRepository userRepository;

    private MailService mailService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
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
    public ResponseEntity<?> createUser(ReqJoinDTO reqJoinDTO) throws MessagingException, IOException {
        if(0!=getChkId(reqJoinDTO.getUserId()) || 0!=getChkEmail(reqJoinDTO.getEmail()) || 0!=getChkNickname(reqJoinDTO.getNickname())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(CustomResponse.builder()
                            .ok(false)
                            .statusCode(HttpStatus.CONFLICT.value())
                            .message("중복확인을 다시 해주세요")
                            .build() );
        }
        // 비밀번호 암호화
        reqJoinDTO.setUserPw(bCryptPasswordEncoder.encode(reqJoinDTO.getUserPw()));

        String code = userRepository.save(reqJoinDTO.toEntity()).getEmailCode();

        // 인증메일 발송
        mailSend(reqJoinDTO,code);

        return ResponseEntity.status(HttpStatus.OK).body(CustomResponse.builder()
                .ok(true).statusCode(HttpStatus.OK.value()).message(reqJoinDTO.getUserId() + " ID로 회원가입을 성공하였습니다.").build());
    }

    // 회원가입 인증 메일 발송
    public void mailSend(ReqJoinDTO reqJoinDTO, String code) throws MessagingException, IOException {
        MailDto mailDto = MailDto.builder().address(reqJoinDTO.getEmail())
                .title("회원가입").message("회원가입 메시지").build();

        HashMap<String, String> emailValues = new HashMap<>();
        emailValues.put("id", reqJoinDTO.getUserId());
        emailValues.put("code", code);

        mailService.mailSend(mailDto, emailValues,"join");
    }

    // 메일 인증 처리
    public ResponseEntity<?> enableUser(String userId, String code){
        int chk = 0;
        chk = userRepository.countByUserIdAndEmailCode(userId, code);
        if(chk<1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .ok(false)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("ID가 존재하지 않거나 인증코드가 맞지 않습니다.")
                            .build());
        }else {
            User user = userRepository.findByUserIdAndEmailCode(userId, code);
            user.setUseYn("Y");
            userRepository.save(user);
        }

        return ResponseEntity.status(HttpStatus.OK).body(CustomResponse.builder().ok(true)
                .statusCode(HttpStatus.OK.value())
                .message("이메일 인증에 성공하였습니다.")
                .build());
    }

    @Transactional
    @Scheduled(cron = "0 0 17 * * *") // 매일 15시 실행
    public void deleteExpiredId(){
        int i = userRepository.deleteByEmailExDateBeforeAndUseYn(new Timestamp(System.currentTimeMillis()),"K");
        System.out.println("삭제 성공 ? : " + i);
    }

}
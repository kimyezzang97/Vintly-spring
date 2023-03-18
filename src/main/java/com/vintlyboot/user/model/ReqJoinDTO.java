package com.vintlyboot.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vintlyboot.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Getter @Setter
public class ReqJoinDTO {
    // 회원 ID
    @NotBlank(message = "ID를 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9]{4,20}$", message = "ID는 영어와 숫자만 사용하여 4~20자로 입력해주세요.")
    String userId;

    // 회원 비밀번호
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",message = "비밀번호는 영어,숫자,특수문자를" +
            " 사용하여 8~20자로 입력해주세요.")
    String userPw;

    // 회원이름
    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "(^[가-힣]{2,10}$)|(^[a-zA-Z]{2,20}$)", message = "이름은 영어 혹은 한글 2~20자로 입력해주세요.")
    String userName;

    // 닉네임
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣A-Za-z0-9_-]{1,15}$", message = "영어,한글 혹은 '-','_' 으로 1~15자로 입력해주세요.")
    String nickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 이거를 사용해도됨
    Date birth;

    // 이메일
    @Email
    @NotBlank(message = "이메일을 입력해주세요")
    String email;

    // 기본 주소
    @NotBlank(message = "기본주소를 입력해주세요.")
    String addr1;

    // 상세 주소
    @NotEmpty(message = "상세주소를 입력해주세요.")
    String addr2;

    // 성별 M : 남자, W : 여자, U : 알 수 없음
    @NotEmpty(message = "성별을 체크해주세요.")
    String gender;

    public User toEntity(){
        return User.builder().userId(userId).userPw(userPw).userName(userName)
                .nickname(nickname).birth(birth).email(email).addr1(addr1)
                .addr2(addr2).gender(gender).build();
    }
}

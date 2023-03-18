package com.vintlyboot.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "vintly", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idx")
    private int idx;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "user_pw")
    private String userPw;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "birth")
    private Date birth;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "addr1")
    private String addr1;
    @Basic
    @Column(name = "addr2")
    private String addr2;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "use_yn")
    private String useYn;
    @Basic
    @Column(name = "reg_date")
    private Timestamp regDate;
    @Basic
    @Column(name = "pw_date")
    private Timestamp pwDate;
    @Basic
    @Column(name = "del_date")
    private Timestamp delDate;
    @Basic
    @Column(name = "email_code")
    private String emailCode;
    @Basic
    @Column(name = "email_ex_date")
    private Timestamp emailExDate;
    @Basic
    @Column(name = "phone_code")
    private String phoneCode;


    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }

    public Timestamp getPwDate() {
        return pwDate;
    }

    public void setPwDate(Timestamp pwDate) {
        this.pwDate = pwDate;
    }

    public Timestamp getDelDate() {
        return delDate;
    }

    public void setDelDate(Timestamp delDate) {
        this.delDate = delDate;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public Timestamp getEmailExDate() {
        return emailExDate;
    }

    public void setEmailExDate(Timestamp emailExDate) {
        this.emailExDate = emailExDate;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idx == that.idx && Objects.equals(userId, that.userId) && Objects.equals(userPw, that.userPw) && Objects.equals(userName, that.userName) && Objects.equals(birth, that.birth) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(addr1, that.addr1) && Objects.equals(addr2, that.addr2) && Objects.equals(gender, that.gender) && Objects.equals(useYn, that.useYn) && Objects.equals(regDate, that.regDate) && Objects.equals(pwDate, that.pwDate) && Objects.equals(delDate, that.delDate) && Objects.equals(emailCode, that.emailCode) && Objects.equals(emailExDate, that.emailExDate) && Objects.equals(phoneCode, that.phoneCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, userId, userPw, userName, birth, email, phone, addr1, addr2, gender, useYn, regDate, pwDate, delDate, emailCode, emailExDate, phoneCode);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

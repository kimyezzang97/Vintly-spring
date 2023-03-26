package com.vintlyboot.util;

import com.vintlyboot.util.model.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;

@Service
public class MailService{


    private JavaMailSender mailSender;

    private SpringTemplateEngine templateEngine;

    @Autowired
    public MailService(SpringTemplateEngine springTemplateEngine, JavaMailSender javaMailSender){
        this.templateEngine = springTemplateEngine;
        this.mailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String fromAddress;

    public void mailSend(MailDto mailDto, HashMap<String, String> values, String htmlName) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //메일 제목 설정
        helper.setSubject(mailDto.getTitle());

        // 발신자 설정
        helper.setFrom(fromAddress);

        //수신자 설정
        helper.setTo(mailDto.getAddress());

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        values.forEach((key, value)->{
            context.setVariable(key, value);
        });

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process(htmlName,context);
        helper.setText(html,true);

        mailSender.send(message);
    }
}

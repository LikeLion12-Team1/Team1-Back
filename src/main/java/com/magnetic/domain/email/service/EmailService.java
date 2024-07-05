package com.magnetic.domain.email.service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    public static final String pw = findPassword();

    private static String findPassword() {
        return null;
    }

    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("보내는 대상 : "+ to);
        System.out.println("비밀번호 : "+ pw);
        MimeMessage message = emailSender.createMimeMessage();
        ((MimeMessage) message).addRecipients(MimeMessage.RecipientType.TO, to);//보내는 대상
        message.setSubject("비밀번호 찾기");//제목

        String msgg="";
        msgg+= "<div style='margin:15px;'>";
        msgg+= "<h1> SCREWBAR </h1>";
        msgg+= "<br>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>비밀번호입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "PASSWORD : <strong>";
        msgg+= pw +"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("alslalsl3125@gmail.com","cham"));//보내는 사람

        return message;
    }

    public void sendSimpleMessage(String to)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}

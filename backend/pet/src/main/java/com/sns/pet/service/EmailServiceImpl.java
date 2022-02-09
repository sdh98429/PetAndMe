package com.sns.pet.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sns.pet.dao.EmailDao;
import com.sns.pet.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final SqlSession sqlSession;

    public static String authKey;

    private MimeMessage createMessage(String userEmail) throws Exception {
        System.out.println("보내는 대상 : " + userEmail);
        System.out.println("인증 번호 : " + authKey);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, userEmail);//보내는 대상
        message.setSubject("Pet&me 회원가입 인증번호 이메일입니다.");//제목

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1 style='text-align: center'> Pet&me </h1>";
        msg += "<br/>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<br/>";
        msg += "<p>안녕하세요! <b>pet&me</b>에서 알려드립니다. </p>";
        msg += "<p> 이메일 인증을 위한 인증번호가 발급되었습니다. </p>";
        msg += "<p>아래의 인증번호를 복사하여 이메일 인증을 완료해주세요. </p>";
        msg += "<br/>";
        msg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += authKey + "</strong><div><br/> ";
        msg += "</div>";
        message.setText(msg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("petandme.b106@gmail.com", "Pet&me"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer authKey = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
            switch (index) {
                case 0:
                    authKey.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    authKey.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    authKey.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return authKey.toString();
    }

    @Override
    public String sendSimpleMessage(String userEmail) throws Exception {
        authKey = createKey();
        MimeMessage message = createMessage(userEmail);
        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            authKey = "error";
            throw new IllegalArgumentException();
        }
        return authKey;
    }

    @Override
    public boolean addAuthKey(String userEmail, String authKey) throws Exception {
        return sqlSession.getMapper(EmailDao.class).insertAuthKey(userEmail, authKey) == 1;
    }

    @Override
    public boolean updateAuthKey(String userEmail) throws Exception {
        return sqlSession.getMapper(EmailDao.class).updateAuthKey(userEmail) == 1;
    }

    @Override
    public String findAuthKey(String userEmail) throws Exception {
        return sqlSession.getMapper(EmailDao.class).selectAuthKey(userEmail);
    }

}
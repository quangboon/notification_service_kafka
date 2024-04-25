package com.quangboon.notification_service_kafka.service;

import com.quangboon.notification_service_kafka.model.MessageDto;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

public interface EmailService {
    void sendEmail(MessageDto messageDto);

}

@Slf4j
@Service
class EmailServiceIml implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    @Override
    public void sendEmail(MessageDto messageDto) {
        try {
            log.info("#start.... Send email");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name",messageDto.getToName());
            context.setVariable("content",messageDto.getContent());
            String html = templateEngine.process("welcome-email", context);

            helper.setTo(messageDto.getTo());
            helper.setText(html, true);
            helper.setSubject(messageDto.getSubject());
            helper.setFrom(from);
            javaMailSender.send(message);

            log.info("#End... email sent success");

        } catch (Exception e) {
            log.error("#Email sent with error:  ", e.getMessage());
        }
    }
}

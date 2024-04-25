package com.quangboon.notification_service_kafka.service;

import com.quangboon.notification_service_kafka.model.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(MessageDto messageDto){
        log.info("Received: ", messageDto.getTo());
        emailService.sendEmail(messageDto);
    }
}

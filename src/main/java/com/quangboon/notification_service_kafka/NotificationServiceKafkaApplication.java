package com.quangboon.notification_service_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NotificationServiceKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceKafkaApplication.class, args);
    }


    @Bean
    JsonMessageConverter converter() {
        return new JsonMessageConverter();
    }
}

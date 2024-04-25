package com.quangboon.notification_service_kafka.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private String to;
    private String toName;
    private String subject;
    private String content;

}

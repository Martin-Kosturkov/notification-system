package com.mkosturkov.notificationsender.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkosturkov.common.EmailNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${kafka.topics.email-notifications}",
            groupId = "email-notification-consumer",
            concurrency = "5")
    public void consumerEmailNotification(String event) {
        var emailNotification = deserialize(event);

    }

    private EmailNotificationEvent deserialize(String event) {
        try {
            return objectMapper.readValue(event, EmailNotificationEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing email notification event: " + event, e);
        }
    }
}

package com.mkosturkov.notificationprocessor.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkosturkov.common.EmailNotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final String topicName;

    public EmailNotificationKafkaProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            @Value("${kafka.topics.email-notifications.name}") String topicName) {

        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.topicName = topicName;
    }

    public boolean send(EmailNotificationEvent notificationEvent) {
        try {
            kafkaTemplate.send(topicName, objectMapper.writeValueAsString(notificationEvent)).get();
            return true;
        } catch (Exception e) {
            log.error("Error sending email event {}", notificationEvent, e);
            return false;
        }
    }
}

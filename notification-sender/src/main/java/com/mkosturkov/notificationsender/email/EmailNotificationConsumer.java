package com.mkosturkov.notificationsender.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkosturkov.common.EmailNotificationEvent;
import com.mkosturkov.notificationsender.common.exception.RetryableEventException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailNotificationConsumer {

    private EmailNotificationService emailNotificationService;
    private final ObjectMapper objectMapper;

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(
                    delay = 1000 * 60 * 60,
                    multiplier = 6.0,
                    maxDelay = Integer.MAX_VALUE),
            include = RetryableEventException.class)
    @KafkaListener(
            topics = "${kafka.topics.email-notifications.name}",
            groupId = "email-notification-consumer",
            concurrency = "${kafka.topics.email-notifications.partitions}")
    public void consumerEmailNotification(String event) {
        var emailNotification = deserialize(event);

        try {
            EmailNotificationService.collectDataAndSendEmail(emailNotification);
        } catch (Exception e) {
            log.error("Error processing email event with id {}, will be retried",
                    emailNotification.getEventId(), e);
            throw new RetryableEventException();
        }
    }

    private EmailNotificationEvent deserialize(String event) {
        try {
            return objectMapper.readValue(event, EmailNotificationEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid email notification event: " + event, e);
        }
    }
}

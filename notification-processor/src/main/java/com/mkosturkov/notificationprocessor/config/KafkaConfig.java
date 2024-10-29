package com.mkosturkov.notificationprocessor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin.NewTopics emailNotificationsTopic(
            @Value("${kafka.topics.email-notifications}") String emailNotificationsTopic,
            @Value("${kafka.topics.sms-notifications}") String smsNotificationsTopic,
            @Value("${kafka.topics.slack-notifications}") String slackNotificationsTopic) {

        return new KafkaAdmin.NewTopics(
                new NewTopic(emailNotificationsTopic, 5, (short) 1),
                new NewTopic(smsNotificationsTopic, 5, (short) 1),
                new NewTopic(slackNotificationsTopic, 5, (short) 1));
    }
}

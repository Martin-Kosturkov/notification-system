package com.mkosturkov.notificationprocessor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin.NewTopics emailNotificationsTopic() {
        return new KafkaAdmin.NewTopics(
                new NewTopic("email-notifications", 5, (short) 1),
                new NewTopic("sms-notifications", 5, (short) 1),
                new NewTopic("slack-notifications", 5, (short) 1));
    }
}

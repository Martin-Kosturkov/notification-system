package com.mkosturkov.notificationsender.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic emailNotificationsTopic(
            @Value("${kafka.topics.email-notifications.name}") String topicName,
            @Value("${kafka.topics.email-notifications.partitions}") Integer partitions) {

        return new NewTopic(topicName, partitions, (short) 1);
    }

    @Bean
    public NewTopic smsNotificationsTopic(
            @Value("${kafka.topics.sms-notifications.name}") String topicName,
            @Value("${kafka.topics.sms-notifications.partitions}") Integer partitions) {

        return new NewTopic(topicName, partitions, (short) 1);
    }

    @Bean
    public NewTopic slackNotificationsTopic(
            @Value("${kafka.topics.slack-notifications.name}") String topicName,
            @Value("${kafka.topics.slack-notifications.partitions}") Integer partitions) {

        return new NewTopic(topicName, partitions, (short) 1);
    }
}

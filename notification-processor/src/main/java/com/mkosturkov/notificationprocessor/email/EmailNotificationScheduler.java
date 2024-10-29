package com.mkosturkov.notificationprocessor.email;

import com.mkosturkov.common.EmailNotificationEvent;
import com.mkosturkov.notificationprocessor.email.dao.EmailNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class EmailNotificationScheduler {

    private final EmailNotificationRepository repository;
    private final EmailNotificationKafkaProducer kafkaProducer;
    private final TaskExecutor taskExecutor;

    public EmailNotificationScheduler(
            EmailNotificationRepository repository,
            EmailNotificationKafkaProducer kafkaProducer,
            @Qualifier("email-notifications-executor") TaskExecutor taskExecutor) {

        this.repository = repository;
        this.kafkaProducer = kafkaProducer;
        this.taskExecutor = taskExecutor;
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void sendNotificationsToKafka() {
        repository.findFirst20000ByOrderById()
                .forEach(emailNotification ->

                    CompletableFuture.runAsync(() -> {
                        var notificationEvent = new EmailNotificationEvent(
                                emailNotification.getId(),
                                emailNotification.getTemplate(),
                                emailNotification.getPayload(),
                                emailNotification.getRecipientId());

                        var isSuccessfullySend = kafkaProducer.send(notificationEvent);

                        if (isSuccessfullySend) {
                            repository.deleteById(emailNotification.getId());
                        }
                    }, taskExecutor)
                );
    }
}

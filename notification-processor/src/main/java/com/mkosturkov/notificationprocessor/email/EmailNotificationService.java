package com.mkosturkov.notificationprocessor.email;

import com.mkosturkov.notificationprocessor.email.api.EmailNotificationData;
import com.mkosturkov.notificationprocessor.email.dao.EmailNotificationEntity;
import com.mkosturkov.notificationprocessor.email.dao.EmailNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final EmailNotificationRepository repository;

    public void splitAndSaveEmailNotificationPerRecipientId(EmailNotificationData emailNotification) {
        var notificationPayload = emailNotification.getPayload();

        var emailNotificationEntities = emailNotification.getRecipientIds().stream()
                .map(recipientId -> {

                    var entity = new EmailNotificationEntity();

                    entity.setRecipientId(recipientId);
                    entity.setPayload(notificationPayload);
                    entity.setTemplate(emailNotification.getTemplateName());

                    return entity;
                })
                .toList();

        repository.saveAll(emailNotificationEntities);
    }
}

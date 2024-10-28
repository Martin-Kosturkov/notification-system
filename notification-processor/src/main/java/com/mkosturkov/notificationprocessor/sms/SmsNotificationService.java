package com.mkosturkov.notificationprocessor.sms;

import com.mkosturkov.notificationprocessor.sms.api.SmsNotificationRequest;
import com.mkosturkov.notificationprocessor.sms.dao.SmsNotificationEntity;
import com.mkosturkov.notificationprocessor.sms.dao.SmsNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsNotificationService {

    private final SmsNotificationRepository repository;

    public void splitAndSaveNotificationsByRecipientId(SmsNotificationRequest notificationRequest) {

        var smsNotificationEntities = notificationRequest.getRecipientIds().stream()
                .map(recipientId -> {
                    var entity = new SmsNotificationEntity();

                    entity.setRecipientId(recipientId);
                    entity.setMessage(notificationRequest.getMessage());
                    return entity;
                }).toList();

        repository.saveAll(smsNotificationEntities);
    }
}

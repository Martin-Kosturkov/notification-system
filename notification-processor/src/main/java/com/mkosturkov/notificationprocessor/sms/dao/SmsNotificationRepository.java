package com.mkosturkov.notificationprocessor.sms.dao;

import org.springframework.data.repository.CrudRepository;

public interface SmsNotificationRepository extends CrudRepository<SmsNotificationEntity, Long> {
}

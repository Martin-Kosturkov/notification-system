package com.mkosturkov.notificationprocessor.email.dao;

import org.springframework.data.repository.CrudRepository;

public interface EmailNotificationRepository extends CrudRepository<EmailNotificationEntity, Long> {
}

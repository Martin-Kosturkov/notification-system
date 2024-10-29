package com.mkosturkov.notificationprocessor.email.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailNotificationRepository extends CrudRepository<EmailNotificationEntity, Long> {

    List<EmailNotificationEntity> findFirst20000ByOrderById();
}

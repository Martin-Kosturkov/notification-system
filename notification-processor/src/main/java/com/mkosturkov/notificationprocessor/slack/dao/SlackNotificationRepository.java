package com.mkosturkov.notificationprocessor.slack.dao;

import org.springframework.data.repository.CrudRepository;

public interface SlackNotificationRepository extends CrudRepository<SlackNotificationEntity, Long> {
}

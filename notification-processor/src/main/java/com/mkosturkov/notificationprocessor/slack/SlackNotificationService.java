package com.mkosturkov.notificationprocessor.slack;

import com.mkosturkov.notificationprocessor.slack.api.SlackNotificationRequest;
import com.mkosturkov.notificationprocessor.slack.dao.SlackNotificationEntity;
import com.mkosturkov.notificationprocessor.slack.dao.SlackNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlackNotificationService {

    private final SlackNotificationRepository notificationRepository;

    public void save(SlackNotificationRequest notificationRequest) {

        var entity = new SlackNotificationEntity();
        entity.setMessage(notificationRequest.getMessage());
        entity.setChannelName(notificationRequest.getChannelName());
        entity.setOrgId(notificationRequest.getOrgId());

        notificationRepository.save(entity);
    }
}

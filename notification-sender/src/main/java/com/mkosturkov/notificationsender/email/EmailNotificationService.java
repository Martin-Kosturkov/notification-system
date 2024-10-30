package com.mkosturkov.notificationsender.email;

import com.mkosturkov.common.email.EmailNotificationEvent;
import com.mkosturkov.notificationsender.common.orgmetadata.OrgMetadataClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final OrgMetadataClient orgMetadataClient;

    public void processAndSendEmail(EmailNotificationEvent emailNotification) {

    }
}

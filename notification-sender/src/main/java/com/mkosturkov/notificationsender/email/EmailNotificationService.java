package com.mkosturkov.notificationsender.email;

import com.mkosturkov.common.email.EmailNotificationEvent;
import com.mkosturkov.common.email.EmailTemplateType;
import com.mkosturkov.notificationsender.common.orgmetadata.OrgMetadataClient;
import com.mkosturkov.notificationsender.email.template.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmailNotificationService {

    private final OrgMetadataClient orgMetadataClient;
    private final EmailSender emailSender;
    private final Map<EmailTemplateType, EmailTemplate> templateTypeToProcessor;

    public EmailNotificationService(OrgMetadataClient orgMetadataClient,
                                    EmailSender emailSender,
                                    List<EmailTemplate> emailTemplateList) {

        this.orgMetadataClient = orgMetadataClient;
        this.emailSender = emailSender;

        templateTypeToProcessor = emailTemplateList.stream()
                .collect(Collectors.toMap(
                        EmailTemplate::getTemplateType,
                        Function.identity()));
    }

    public void processAndSendEmail(EmailNotificationEvent emailNotification) {
        var userMetadata = orgMetadataClient.getUserMetadataById(emailNotification.getRecipientId());

        if (userMetadata.isEmpty()) {
            log.warn("Metadata for user with id {} was not found for event with id {}",
                    emailNotification.getRecipientId(), emailNotification.getEventId());
        }

        var emailProperties = getEmailPropertiesBasedOnTemplate(emailNotification);

        emailSender.send(emailProperties);
        log.info("Successfully processed email event {}", emailNotification);
    }

    private EmailProperties getEmailPropertiesBasedOnTemplate(EmailNotificationEvent emailNotification) {
        var payload = emailNotification.getPayload();

        return switch (emailNotification.getTemplate()) {
            case SIMPLE -> templateTypeToProcessor.get(EmailTemplateType.SIMPLE).toEmailProperties(payload);
        };
    }
}

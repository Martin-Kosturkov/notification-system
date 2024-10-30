package com.mkosturkov.notificationsender.email.template;

import com.mkosturkov.common.email.EmailTemplateType;
import com.mkosturkov.notificationsender.email.EmailProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimpleEmailTemplate implements EmailTemplate {

    @Override
    public EmailTemplateType getTemplateType() {
        return EmailTemplateType.SIMPLE;
    }

    @Override
    public EmailProperties toEmailProperties(Map<String, Object> payload) {
        return EmailProperties.builder()
                .message((String) payload.get("message"))
                .build();
    }
}

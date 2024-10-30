package com.mkosturkov.notificationsender.email.template;

import com.mkosturkov.common.email.EmailTemplateType;
import com.mkosturkov.notificationsender.email.EmailProperties;

import java.util.Map;

public interface EmailTemplate {
    EmailTemplateType getTemplateType();
    EmailProperties toEmailProperties(Map<String, Object> payload);
}

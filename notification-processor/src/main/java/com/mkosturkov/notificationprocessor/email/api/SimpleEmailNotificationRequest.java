package com.mkosturkov.notificationprocessor.email.api;

import com.mkosturkov.common.email.EmailTemplateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Setter
public class SimpleEmailNotificationRequest implements EmailNotificationData {
    private static final String TEMPLATE_NAME = EmailTemplateType.SIMPLE.toString();

    @NotBlank(message = "message cannot be empty")
    @Size(max = 2000, message = "message size cannot be bigger than 2000 characters")
    private String message;

    @NotEmpty(message = "recipientIds cannot be empty")
    @Size(max = 1000, message = "recipientIds cannot be more than 1000")
    private Set<Integer> recipientIds;

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }

    @Override
    public Map<String, Object> getPayload() {
        return Map.of("message", message);
    }

    @Override
    public Set<Integer> getRecipientIds() {
        return recipientIds;
    }
}

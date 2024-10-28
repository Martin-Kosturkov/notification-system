package com.mkosturkov.notificationprocessor.email.api;

import java.util.Map;
import java.util.Set;

public interface EmailNotificationData {
    String getTemplateName();
    Map<String, Object> getPayload();
    Set<Integer> getRecipientIds();
}

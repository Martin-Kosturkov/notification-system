package com.mkosturkov.notificationprocessor.sms;

import com.mkosturkov.notificationprocessor.sms.api.SmsNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/sms")
@RequiredArgsConstructor
public class SmsNotificationController {

    private final SmsNotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> handleSmsNotification(@Validated @RequestBody SmsNotificationRequest notificationRequest) {
        notificationService.splitAndSaveNotificationsByRecipientId(notificationRequest);
        return ResponseEntity.accepted().build();
    }
}

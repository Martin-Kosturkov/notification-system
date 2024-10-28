package com.mkosturkov.notificationprocessor.email;

import com.mkosturkov.notificationprocessor.email.api.SimpleEmailNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/email")
@RequiredArgsConstructor
public class EmailNotificationController {

    private final EmailNotificationService notificationService;

    @PostMapping("/simple")
    public ResponseEntity<Void> handleSimpleEmail(
            @Validated @RequestBody SimpleEmailNotificationRequest simpleEmailNotification) {

        notificationService.splitAndSaveEmailNotificationPerRecipientId(simpleEmailNotification);
        return ResponseEntity.accepted().build();
    }
}

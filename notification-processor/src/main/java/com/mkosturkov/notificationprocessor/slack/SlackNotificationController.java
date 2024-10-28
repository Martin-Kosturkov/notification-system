package com.mkosturkov.notificationprocessor.slack;

import com.mkosturkov.notificationprocessor.slack.api.SlackNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications/slack")
@RequiredArgsConstructor
public class SlackNotificationController {

    private final SlackNotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> handleSlackNotification(
            @Validated @RequestBody SlackNotificationRequest notificationRequest) {

        notificationService.save(notificationRequest);
        return ResponseEntity.accepted().build();
    }
}

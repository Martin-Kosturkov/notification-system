package com.mkosturkov.notificationprocessor.sms.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SmsNotificationRequest {

    @NotBlank(message = "message cannot be empty")
    @Size(max = 300, message = "message size cannot be bigger than 300 characters")
    private String message;

    @NotEmpty(message = "recipientIds cannot be empty")
    @Size(max = 5000, message = "recipientIds cannot be more than 5000")
    private Set<Integer> recipientIds;
}

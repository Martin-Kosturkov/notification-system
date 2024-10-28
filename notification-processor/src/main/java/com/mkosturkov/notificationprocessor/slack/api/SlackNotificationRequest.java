package com.mkosturkov.notificationprocessor.slack.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlackNotificationRequest {

    @NotBlank(message = "message cannot be empty")
    @Size(max = 1000, message = "message size cannot be bigger than 1000 characters")
    private String message;

    @NotBlank(message = "channelName cannot be empty")
    @Size(max = 60, message = "channelName cannot be bigger than 60 characters")
    private String channelName;

    @NotNull(message = "orgId cannot be empty")
    private Integer orgId;
}

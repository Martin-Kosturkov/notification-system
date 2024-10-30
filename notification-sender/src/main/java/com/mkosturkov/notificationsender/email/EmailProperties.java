package com.mkosturkov.notificationsender.email;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EmailProperties {
    private String receiver;
    private String message;
}

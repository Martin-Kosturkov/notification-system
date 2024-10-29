package com.mkosturkov.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmailNotificationEvent {
    private String template;
    private Map<String, Object> payload;
    private Integer recipientId;
}

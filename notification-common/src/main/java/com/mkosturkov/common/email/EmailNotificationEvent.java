package com.mkosturkov.common.email;

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
    private Long eventId;
    private EmailTemplateType template;
    private Map<String, Object> payload;
    private Integer recipientId;
}

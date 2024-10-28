package com.mkosturkov.notificationprocessor.email.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Table(name = "email")
@Getter
@Setter
public class EmailNotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template")
    private String template;

    @Column(name = "payload")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payload;

    @Column(name = "recipient_id")
    private Integer recipientId;
}

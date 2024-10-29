package com.mkosturkov.orgmetadata.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private Integer id;
    private String email;
    private String phoneNumber;
}

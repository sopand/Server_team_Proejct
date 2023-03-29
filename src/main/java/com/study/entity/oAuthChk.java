package com.study.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum oAuthChk {

    OAUTH_USER("Y"),
    OAUTH_USER_PASSWORD_ON("ON"),
    NO_OAUTH_USER("N");


    private final String Status;
}

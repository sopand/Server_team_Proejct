package com.study.oAuth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum oAuthChk {

    OAUTH_USER("Y"),
    NO_OAUTH_USER("N");


    private final String Status;
}

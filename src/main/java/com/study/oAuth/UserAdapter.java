package com.study.oAuth;

import com.study.entity.User;
import com.study.oAuth.OAuth2UserInfo;
import com.study.oAuth.PrincipalDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserAdapter extends PrincipalDetails {
    private User user;
    private OAuth2UserInfo oAuth2UserInfo;

    public UserAdapter(User user) {
        super(user);
        this.user=user;
    }

    public UserAdapter(User user, OAuth2UserInfo oAuth2UserInfo) {
        super(user, oAuth2UserInfo);
        this.user=user;
        this.oAuth2UserInfo=oAuth2UserInfo;
    }
}

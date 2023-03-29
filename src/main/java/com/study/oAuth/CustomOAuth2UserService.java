package com.study.oAuth;

import com.study.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {

    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;	//추가
        String provider = userRequest.getClientRegistration().getRegistrationId();

        //추가
        if(provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String userName = oAuth2UserInfo.getName();

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String pwd = passwordEncoder.encode("패스워드"+uuid);

        String email = oAuth2UserInfo.getEmail();	//수정
        Role role = Role.ROLE_USER;
        User byUsername = userRepository.findByEmail(email).orElse(null);
        Sport sportNo=Sport.builder().spoNo(1L).build();

        if(byUsername == null){
            byUsername=User.builder().name(userName).sport(sportNo).password(pwd).email(email).role(role).oauthMemberCheck(oAuthChk.OAUTH_USER.getStatus()).build();
            userRepository.save(byUsername);
        }

        return new PrincipalDetails(byUsername, oAuth2UserInfo);	//수정
    }
}

package com.study.config;


import com.study.oAuth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    public static final String[] SECURITY_EXCLUDE_PATTERN_ARR = {
            "/css/**", "/font/**", "/cmsimg/**", "/favicon.ico",
            "/error", "/css/js/**"};

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // css,js,img등의 시큐리티 필터적용이 필요없는 자원에 대한 접근을 설정,
        return (web) -> web.ignoring().requestMatchers(SECURITY_EXCLUDE_PATTERN_ARR);
    }

    @Bean
    public SecurityFilterChain StudentFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/**","/users/**")
                .permitAll();
        http.formLogin() // LOGIN폼을 설정해줄때 작성 ,
                .loginPage("/users/login") // 로그인을 실행하게될 FORM페이지로의 이동
                .loginProcessingUrl("/users/login") // 해당하는 url로 접근시 로그인의 기능이 작동, request되는 값을 컨트롤러가 아닌 이곳으로 넘겨받음.
                .usernameParameter("email") // default= Username 파라미터로 넘어오는 값의 이름을 설정
                .passwordParameter("password")// default =Password
                .defaultSuccessUrl("/index",true) // 로그인 성공시 이동하는 페이지
                .permitAll().and()
                .oauth2Login().loginPage("/users/login")
                .defaultSuccessUrl("/index", true)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }

}

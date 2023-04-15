package com.study.entity;

import com.study.config.DataBaseConfig;
import com.study.dto.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(DataBaseConfig.class)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void beforeCreateUser() throws ParseException {
        userRepository.save(createUserRequest().toCreateUserEntity());
    }
    @Test
    @DisplayName("이메일로 USER 찾아오기")
    void findByEmail() {
        //given
        UserRequest stubUserRequest=createUserRequest();
        //when
        User user=userRepository.findByEmail(stubUserRequest.getEmail()).orElse(null);
        //then
        assertThat(user).isNotNull();
        assertThat(stubUserRequest.getEmail()).isEqualTo(user.getEmail());
        log.info("User객체 데이터 유무 :{}",user.getEmail());
    }

    private UserRequest createUserRequest(){
        UserRequest stubUserRequest=new UserRequest();
        stubUserRequest.setPassword("TestPass");
        stubUserRequest.setName("TestName");
        stubUserRequest.setNickname("TestNick");
        stubUserRequest.setEmail("Test@Test.com");
        stubUserRequest.setSportNo(1L);
        return stubUserRequest;

    }
}
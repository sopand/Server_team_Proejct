package com.study.service;

import com.study.dto.UserRequest;
import com.study.dto.UserResponse;
import com.study.entity.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Transactional
@Slf4j
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void beforeCreateUser() throws ParseException {
        userRepository.save(createUserRequest().toCreateUserEntity());
    }

    private UserRequest createUserRequest() {
        UserRequest stubUserRequest = new UserRequest();
        stubUserRequest.setPassword(passwordEncoder.encode("TestPass"));
        stubUserRequest.setPasswordCheck("TestPass");
        stubUserRequest.setBeforePassword("TestPass");
        stubUserRequest.setName("TestName");
        stubUserRequest.setNickname("TestNick");
        stubUserRequest.setEmail("Test@Test.com");
        stubUserRequest.setSportNo(1L);
        stubUserRequest.setNo(1L);
        return stubUserRequest;

    }

    @Test
    @DisplayName("유저 생성 하기")
    void createUser() throws Exception {
        //given
        UserRequest getUserRequest = createUserRequest();
        //stub
        when(userRepository.save(any())).thenReturn(getUserRequest.toTestCreateEntity());
        //when
        Long getUserNo = userService.createUser(getUserRequest);
        //then
        assertThat(getUserNo).isEqualTo(getUserRequest.getNo());
        assertThat(getUserNo).isNotNull();
    }

    @Test
    @DisplayName("이메일로 해당하는 유저의 정보를 찾아오기")
    void findUserByEmail() throws Exception {
        //given
        UserRequest getUserRequest = createUserRequest();

        //stub
        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(getUserRequest.toTestCreateEntity()));

        //when
        UserResponse user=userService.findUserByEmail(getUserRequest.getEmail());


        // then
        assertThat(user.getEmail()).isEqualTo(getUserRequest.getEmail());
        assertThat(user.getEmail()).isNotNull();

    }

    @Test
    @DisplayName("유저 정보 수정하기")
    void modifyUser() throws Exception {
        //given
        UserRequest getUserRequest = createUserRequest();
        UserRequest modifyUserRequest = createUserRequest();
        modifyUserRequest.setName("Modify");
        modifyUserRequest.setEmail("ModifyEmail");
        modifyUserRequest.setPassword("TestPass");
        System.out.println(modifyUserRequest);
        System.out.println(getUserRequest);
        //stub
        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(getUserRequest.toTestCreateEntity()));

        //when
        userService.modifyUser(modifyUserRequest);

        //then
        verify(userService,times(1)).modifyUser(modifyUserRequest);


    }



}
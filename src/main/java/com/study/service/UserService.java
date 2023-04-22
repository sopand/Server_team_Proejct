package com.study.service;

import com.study.dto.UserRequest;
import com.study.dto.UserResponse;
import com.study.entity.User;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;
import com.study.oAuth.UserAdapter;
import com.study.entity.UserRepository;
import com.study.oAuth.oAuthChk;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 패스워드 암호화를 위한 용도

    /**
     * 시큐리티에서 로그인 하려는 사용자의 정보를 찾아오기위한 로직
     * @param email the username identifying the user whose data is required.
     * @return  = 어뎁터 패턴이라고 하던데 아직은 애매하다.. UserAdapter클래스를 통해 가지고 올 사용자의 정보를 커스텀하기 위한 용도다.
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email)  {
        User user=userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EMAIL));
        return new UserAdapter(user);
    }

    /**
     * 유저 회원가입 기능을 담당하는 로직,
     * @param userRequest = 사용자가 입력한 아이디에 대한 데이터가 저장되있는 객체
     * @return = 회원가입 성공여부를 체크하기 위한 값, User가 생성되면 자동 할당되는 고유번호
     * @throws Exception
     */
    @Transactional
    public Long createUser(UserRequest userRequest) throws Exception {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.CREATE_EMAIL_DUPLICATION);
        }
        PasswordCheck(userRequest);
        User user = userRepository.save(userRequest.toCreateUserEntity());
        return user.getNo();
    }

    /**
     * 회원가입시 해당하는 이메일이 중복되는지 확인하기 위한 로직
     * @param email = 찾으려는 이메일의 값
     * @return = 존재한다면 데이터를 반환 ,아니라면 NUll 반환
     */
    @Transactional(readOnly = true)
    public UserResponse findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserResponse::new).orElse(null);
    }

    /**
     * 유저에 대한 정보 수정을 위한 기능 로직으로, 간편로그인 아이디와 , 일반 회원 아이디에 따라 다르게 수정 ( 간편은 비밀번호 X )
     * @param userRequest = 수정하려는 데이터가 저장된 객체
     * @throws Exception
     */
    @Transactional
    public void modifyUser(UserRequest userRequest) throws Exception {
        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER_EMAIL));
        if (user.getOauthMemberCheck().equals(oAuthChk.OAUTH_USER.getStatus())) {
            user.oAuthUserUpdate(userRequest);
            return ;
        }
        if (!passwordEncoder.matches(userRequest.getBeforePassword(), user.getPassword())) { // 일반 회원가입시 패스워드 비교
            throw new CustomException(ErrorCode.BEFORE_PASSWORD_UN_PASSWORD);
        }
        PasswordCheck(userRequest);
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.NormalUserUpdate(userRequest);
    }

    /**
     * 자주쓰이는 패스워드 채크를 하기위한 로직
     * @param userRequest = 패스워드를 저장하고있는 객체
     * @throws CustomException = 틀리면 패스워드가 틀렸다는 에러코드 발생
     */
    public static void PasswordCheck(UserRequest userRequest)  {
        if (!userRequest.getPassword().equals(userRequest.getPasswordCheck())) {
            throw new CustomException(ErrorCode.PASSWORD_UN_PASSWORD_CHECK);
        }
    }


}


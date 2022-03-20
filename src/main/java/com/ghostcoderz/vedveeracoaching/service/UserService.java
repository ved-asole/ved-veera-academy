package com.ghostcoderz.vedveeracoaching.service;

import com.ghostcoderz.vedveeracoaching.entity.SecurityUser;
import com.ghostcoderz.vedveeracoaching.respository.SecurityUserRepository;
import com.ghostcoderz.vedveeracoaching.security.SecurityConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class UserService {

    private final SecurityUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    public UserService(SecurityUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = (BCryptPasswordEncoder) SecurityConfiguration.getPasswordEncoder();
    }

    public void saveUser() {
    }

    public SecurityUser saveAdminUser(){
        String encodedPassword = passwordEncoder.encode("pass");
        SecurityUser user = new SecurityUser("admin", encodedPassword , true, "ROLE_USER,ROLE_ADMIN");
        logger.info("Added Admin User to the Security Users Table");
        return userRepository.save(user);
    }

}
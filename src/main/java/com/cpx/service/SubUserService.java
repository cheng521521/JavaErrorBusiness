package com.cpx.service;

import com.cpx.entity.UserEntity;
import com.cpx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cpx
 * @Description
 * @date 2021/12/22
 */
@Service
@Slf4j
public class SubUserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createSubUserWithExceptionWrong(UserEntity entity) {
        log.info("createSubUserWithExceptionWrong start");
        userRepository.save(entity);
        throw new RuntimeException("invaild status");
    }
}

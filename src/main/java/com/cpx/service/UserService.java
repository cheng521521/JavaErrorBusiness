package com.cpx.service;

import com.cpx.entity.UserEntity;
import com.cpx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author cpx
 * @Description
 * @date 2021/12/20
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubUserService subUserService;

    //标记了@Transaction的private方法
    @Transactional
    public void createUserPrivate(UserEntity entity) {
        userRepository.save(entity);
        if (entity.getName().contains("test")) {
            throw new RuntimeException("invalid username");
        }
    }

    //根据用户名查询用户数
    public int getUserCount(String name) {
        return userRepository.findByName(name).size();
    }

    //异常无法传播出方法，导致事务无法回滚
    @Transactional
    public void createUserWrong1(String name) {
        try {
            userRepository.save(new UserEntity(name));
            throw new RuntimeException("error");
        } catch (Exception ex) {
            log.error("create user failed", ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    //即使出现了受检异常也无法让事务回滚
    @Transactional(rollbackFor = Exception.class)
    public void createWrong2(String name) throws IOException {
        userRepository.save(new UserEntity(name));
        othreTask();
    }

    private void othreTask() throws IOException {
        Files.readAllLines(Paths.get("file-that-no-exist"));
    }

    @Transactional
    public void createUserWrong(UserEntity entity) {
        createMainUser(entity);
        try {
            subUserService.createSubUserWithExceptionWrong(entity);
        } catch (Exception ex) {
            log.error("create sub user error:{}", ex.getMessage());
        }
    }

    private void createMainUser(UserEntity entity) {
        userRepository.save(entity);
        log.info("createMainUser finish");
    }
}

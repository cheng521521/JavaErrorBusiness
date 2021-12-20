package com.cpx;

import com.cpx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}

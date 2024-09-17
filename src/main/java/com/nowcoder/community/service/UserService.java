package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 * 负责处理用户相关的业务逻辑，通过调用数据访问层（Mapper）的方法来获取用户数据。
 */
@Service
public class UserService {

    // 注入用户数据访问对象 UserMapper
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户 ID 查询用户
     *
     * @param id 用户 ID
     * @return 用户信息
     */
    public User findUserById(int id) {
        // 调用 UserMapper 的 selectById 方法，获取用户信息
        return userMapper.selectById(id);
    }
}

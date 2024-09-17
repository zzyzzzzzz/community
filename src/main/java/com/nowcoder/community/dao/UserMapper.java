package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //搜索用户
    User selectById(int id);
    User selectByName(String name);
    User selectByEmail(String email);

    //加用户 修改用户内容
    int insertUser(User user);
    int updateStatus(int id, int status);
    int updateHeader(int id, String headerUrl);
    int updatePassword(int id,String password);
}

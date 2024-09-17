package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    /**
     * 测试查询用户信息的方法
     */
    @Test
    public void testSelectUser(){
        // 测试通过 ID 查询用户
        User user = userMapper.selectById(101);
        System.out.println(user);

        // 测试通过用户名查询用户
        user = userMapper.selectByName("liubei");
        System.out.println(user);

        // 测试通过邮箱查询用户
        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    /**
     * 测试插入用户的方法
     */
    @Test
    public void testInsertUser(){
        User user = new User();
        // id 不用设置，因为数据库会自动生成
        user.setUsername("test");
        user.setPassword("testpassword");
        user.setSalt("testsalt");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://images.nowcoder.com/head/100t.png");
        user.setCreateTime(new Date());

        // 执行插入操作，并返回插入的行数
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId()); // 输出插入后的用户 ID
    }

    /**
     * 测试更新用户信息的方法
     */
    @Test
    public void testUpdateUser(){
        // 测试更新用户状态
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        // 测试更新用户密码
        rows = userMapper.updatePassword(150, "newpassword");
        System.out.println(rows);
    }

    /**
     * 测试查询讨论帖的方法
     */
    @Test
    public void testSelectPosts(){
        // 查询帖子列表，分页显示前 10 条帖子
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost post : list) {
            System.out.println(post);
        }

        // 查询所有帖子的总数
        int rows = discussPostMapper.selectDiscussRows(0);
        System.out.println(rows);

        // 查询特定用户的帖子总数
        int rows2 = discussPostMapper.selectDiscussRows(149);
        System.out.println(rows2);
    }
}

package com.nowcoder.community.controller;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 标识这个类是一个控制器，用来处理用户的请求
@Controller
public class HomeController {

    // 注入业务层 DiscussPostService，用于获取讨论帖的数据
    @Autowired
    private DiscussPostService discussPostService;

    // 注入业务层 UserService，用于获取用户的信息
    @Autowired
    private UserService userService;

    // 处理 "/index" 路径的 GET 请求，即用户访问首页时调用这个方法
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    //页面会传入分页相关的条件 我们用Page进行封装
    public String getIndexPage(Model model, Page page) {
        //方法调用前，SpringMVC会自动实例化Model和Page 并将Page注入Model
        //所以在thymeleaf中可以直接访问Page对象中的数据
        //总行数
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");


        // 调用 discussPostService 的方法，获取首页展示的帖子数据
        // 参数说明：
        // 第一个参数 0 表示查询所有用户的帖子
        // 第二个参数 0 表示从第 0 行开始查询（分页的开始位置）
        // 第三个参数 10 表示一次查询 10 条帖子（分页大小）
        // 改成动态的页面的offset limit
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());

        // 创建一个空的列表 discussPosts，用于存储每个帖子和对应的用户信息
        List<Map<String, Object>> discussPosts = new ArrayList<>();

        // 如果查询到的帖子列表不为空，开始处理每个帖子
        if (list != null) {

            // 遍历帖子列表，逐条处理
            for (DiscussPost post : list) {

                // 创建一个 HashMap，用来存储帖子和用户信息
                Map<String, Object> map = new HashMap<>();

                // 将帖子对象存入 map 中，键是 "post"，值是当前遍历的帖子对象
                map.put("post", post);

                // 调用 userService，通过帖子中的 userId 查找发帖人用户信息
                User user = userService.findUserById(post.getUserId());

                // 将用户对象存入 map 中，键是 "user"，值是对应的用户信息
                map.put("user", user);

                // 将包含帖子和用户信息的 map 添加到 discussPosts 列表中
                discussPosts.add(map);
            }
        }

        // 将包含帖子和用户信息的列表 取名为discussPosts 添加到 model 中
        // "discussPosts" 是键，discussPosts 列表是值，传递到前端页面
        model.addAttribute("discussPosts", discussPosts);

        // 返回首页的视图名称 "index"
        // 视图解析器会自动寻找 templates 文件夹下的 index.html
        return "/index";
    }

}

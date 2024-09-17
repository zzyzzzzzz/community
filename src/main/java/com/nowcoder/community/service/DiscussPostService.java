package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 讨论帖服务类
 * 负责处理讨论帖相关的业务逻辑，通过调用数据访问层（Mapper）的方法来获取数据。
 */
@Service
public class DiscussPostService {

    // 注入讨论帖数据访问对象 DiscussPostMapper
    @Autowired
    private DiscussPostMapper discussPostMapper;

    /**
     * 获取讨论帖列表
     *
     * @param userId 用户 ID（用于过滤帖子）
     * @param offset 起始行数（用于分页）
     * @param limit 每页显示的帖子数（用于分页）
     * @return 讨论帖列表
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        // 调用 DiscussPostMapper 的 selectDiscussPosts 方法，获取讨论帖列表
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    /**
     * 获取讨论帖总数
     *
     * @param userId 用户 ID（用于过滤帖子）
     * @return 讨论帖总数
     */
    public int findDiscussPostRows(int userId) {
        // 调用 DiscussPostMapper 的 selectDiscussRows 方法，获取讨论帖总数
        return discussPostMapper.selectDiscussRows(userId);
    }
}

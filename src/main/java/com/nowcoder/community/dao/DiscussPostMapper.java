package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     * 查询帖子列表，用于显示首页内容。支持分页显示。
     *
     * @param userId 用户ID，如果为0，则表示查询所有用户的帖子；如果不为0，则查询该用户发布的帖子。
     * @param offset 起始行的行号，用于分页功能，表示从结果集的第几行开始。
     * @param limit  每页显示的最大条数，即一次查询显示多少条帖子。
     * @return 帖子列表。
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /**
     * 查询帖子总行数，用于分页计算总页数。
     *
     * @param userId 用户ID，用于查询该用户发布的帖子数。如果为0，则查询所有帖子数。
     * @return 帖子的总行数。
     *
     * @implNote 如果方法有且仅有一个参数，并且这个参数在SQL语句的动态条件中使用了<if>标签，
     *           则需要使用 @Param 注解为该参数取别名，以便 MyBatis 在 XML 映射文件中正确解析。
     */
    int selectDiscussRows(@Param("userId") int userId);

}

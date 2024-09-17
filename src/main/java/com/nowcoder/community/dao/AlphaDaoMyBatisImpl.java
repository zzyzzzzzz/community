package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary//这个注解！可以标注谁是主要的！
public class AlphaDaoMyBatisImpl implements  AlphaDao{
    @Override
    public String select(){
        return "MyBatis";

    }
}

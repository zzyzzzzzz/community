package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository("alphaHibernate")
//访问数据库的bean就加这个注解
//自定义了bean的名字
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select(){
        return  "Hibernate";
    }

}

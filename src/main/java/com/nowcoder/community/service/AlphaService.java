package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Scope("single")//是默认的单例的 不用写
@Scope("prototype") //多例了
public class AlphaService {


    //注入alphadao
    @Autowired
    private AlphaDao alphaDao;

    //加一个构造器
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }



    //增加初始方法

    @PostConstruct //初始方法在构造之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }


    //销毁方法
    @PreDestroy //在销毁对象之前调用他（销毁就没法调用了
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    //一个例子 在alphaservice中调用alphadao
    public String find(){
        return alphaDao.select();//这就是依赖的方式
    }

}

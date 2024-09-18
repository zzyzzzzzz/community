package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {
    //拷贝一下上面的注解
    //调用mailclient

    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void testTextMail(){
        mailClient.sendMail("ziyingzhang12@gmail.com","Test","TestContent");
    }
    @Test
    public void testHtmlMail(){
        //主动调用模板引擎
        //注入！！
        Context context = new Context();
        context.setVariable("username","sunday");

        String content = templateEngine.process("/mail/demo",context);
        System.out.println(content);  //模板引擎是为了帮我们生成动态网页

        mailClient.sendMail("ziyingzhang12@gmail.com","TestHtml",content);

    }
}

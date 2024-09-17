package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String sayhello(){
        return "Hello Spring Boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return  alphaService.find();
    }

    @RequestMapping("/http") //先定义访问路径
    public  void http(HttpServletRequest request, HttpServletResponse response){
        //1.为什么没有返回值只写public  void http()呢 因为通过那个response 对象可以直接向浏览器输出任何数据 不依赖返回值
        //2.如果想要获取请求响应对象就直接在这个方法上  加以声明就行 dispatcher servlet 在调用这个方法的时候 会制动把对象传给你 在底层已经创建好了
        //3.加上对象public  void http(HttpServletRequest request, HttpServletResponse response)
        //4.获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String>enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        //类型
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer = response.getWriter();
                ) {

            writer.write("<h1>牛客网</h1>");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //GET请求

    // /students?current=1&limit=20
    //当前多少个学生 分页1 显示最多20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
        //先定义路径 然后强制请求方式必须是get才能请求到
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10")int limit ){
         //之前是public String getStudents(int current, int limit){
        System.out.println(current);
        System.out.println(limit);
        return  "Some students";
    }
//然后我们在8080/students里面
    //http://localhost:8080/community/alpha/students?current=3&limit=50
    //会发现控制台也可以变



    //还有一种get请求
    // /students/123
    //具体编号的某个学生的信息
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        //用哪个注解返回？public String getStudent(){在括号里加上@PathVariable("id") int id

        System.out.println(id);//然后加上打印
        return "a student";

    }
    //总结在get请求中又两种传递参数的方式 一种是问好拼 一种是把参数拼到路径中，这两种获取参数的方式有所区别 ，所以用不同的注解

    //如果是提交数据呢？一般用POST请求
    //虽然请求方式有很多种，但是一般get post就能解决所有问题了

    //接下来演示post请求
    //要想提交数据 首先要打开一个有 表单 的网页
    //所以我们先在static下面new directory   html
    //然后在Static.html 中新建 student.html作为打开的网页
    //然后在该网页中加上一个简单的文字和表单

    //然后写post
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }


    //怎么返回 响应数据
    //之前都是响应个字符串 很简单
    //那么怎么响应一个动态的html数据？

    @RequestMapping(path = "teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        //返回的是model和view两份数据 所有主键都是dispatcher servlet返回的 都提交给模板引擎...
        //先实例化对象
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");//模板放到template下面的demo里view.html
        return mav;


        //补完view.html可以成功访问http://localhost:8080/community/alpha/teacher
    }



    //还有一种返回网页的响应
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        //注意这里是String了public String getSchool()
        //上面的方法是把model view的数据都装进去
        //这个是把model装参数里

        model.addAttribute("name","北京大学");
        model.addAttribute("age","100");
        return "/demo/view";
    }


    //响应JSON数据（异步请求）
    //什么是异步请求？  比如注册的时候页面会查用户名有没有重复 这时候界面没有刷新也没有提交

    //异步请求（Asynchronous Request）是指客户端发起请求到服务器，并在等待服务器响应的同时，继续执行其他操作。
    // 异步请求不阻塞用户界面的操作，用户可以在后台加载数据的同时继续浏览页面。

    //返回的结果并不是html

    //java对象 ->JSON字符串 ->JS对象
    //Java 对象到 JSON 字符串：服务器端将 Java 对象序列化为 JSON 字符串。
    //JSON 字符串到 JavaScript 对象：客户端解析 JSON 字符串为 JavaScript 对象，以便在前端使用这些数据。

    @RequestMapping(path = "/employee", method = RequestMethod.GET )
    @ResponseBody
    public Map<String, Object> getEmployee(){
        Map<String,Object> employee = new HashMap<>();
        employee.put("name","Alex");
        employee.put("age",25);
        employee.put("salary",10000);
        return employee;
    }

    //多个
    @RequestMapping(path = "/employees", method = RequestMethod.GET )
    @ResponseBody
    public List<Map<String, Object>> getEmployees(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String,Object> employee = new HashMap<>();
        employee.put("name","Alex");
        employee.put("age",25);
        employee.put("salary",10000);
        list.add(employee);

        employee = new HashMap<>();
        employee.put("name","James");
        employee.put("age",21);
        employee.put("salary",20000);
        list.add(employee);

        employee = new HashMap<>();
        employee.put("name","Quinn");
        employee.put("age",29);
        employee.put("salary",30000);
        list.add(employee);

        return list;
    }

}

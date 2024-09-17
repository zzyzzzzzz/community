package com.nowcoder.community.entity;

//封装分页相关的信息
public class Page {
    //当前页码
    private int current = 1; //默认是1
    //显示上线
    private int limit = 10;
    //查出来的数据总数 用于计算总的页数
    private int rows;
    //查询路径 页面都是一个个链接 处理链接就得带 路径
    //用于复用 分页链接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        //设置条件判断
        if(current>=1){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1 && limit<=100){
            //设置一下页面最多100条
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //还需要补充几个方法额外补充几个条件

    //获取当前页的起始行
    public int getOffset(){
        //current * limit - limit
        return (current - 1)*limit;
    }
    //获取总页数
    public int getTotal(){
        //rows/limit 不整除就+1
        if(rows%limit ==0){
            return rows/limit;
        }else {
            return rows/limit + 1;}
    }

    //从第几页显示到第几页 比如当前页的前两页后两页
    public int getFrom(){
        int from = current -2;
        return from<1 ? 1 :from;
    }
    public int getTo(){
        int to = current +2;
        int total = getTotal();
        return to>total ? total:to;
    }
}

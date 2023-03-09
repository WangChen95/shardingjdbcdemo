package com.iflytek.shardingjdbcdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iflytek.shardingjdbcdemo.entity.Course;
import com.iflytek.shardingjdbcdemo.entity.Dict;
import com.iflytek.shardingjdbcdemo.entity.Order;
import com.iflytek.shardingjdbcdemo.entity.User;
import com.iflytek.shardingjdbcdemo.mapper.CourseMapper;
import com.iflytek.shardingjdbcdemo.mapper.DictMapper;
import com.iflytek.shardingjdbcdemo.mapper.OrderMapper;
import com.iflytek.shardingjdbcdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingjdbcdemoApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private OrderMapper orderMapper;

    //===================测试proxy===========
    @Test
    public void addOrder(){
        Order order = new Order();
        order.setOrderName("手机订单");
        order.setStatus("已付款");
        orderMapper.insert(order);
    }

    @Test
    public void findOrder(){
        QueryWrapper<Dict> wrapper=new QueryWrapper<>();
        wrapper.eq("id",1620707449930936322L);
        dictMapper.delete(wrapper);
    }



    //===================测试广播表===========
    @Test
    public void addDict(){
        Dict dict = new Dict();
        dict.setStatus("a");
        dict.setValue("正常");
        dictMapper.insert(dict);
    }

    @Test
    public void deleteDict(){
        QueryWrapper<Dict> wrapper=new QueryWrapper<>();
        wrapper.eq("id",1620707449930936322L);
        dictMapper.delete(wrapper);
    }

    //===================测试专库专表===========
    @Test
    public void addUserDb(){
        User user = new User();
        user.setUserName("jack");
        user.setUstatus("奋斗中");
        userMapper.insert(user);
    }

    @Test
    public void findUserDb(){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",827928102091882497L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }


    //===================测试水平分库分表===========
    @Test
    public void addCourseDb(){
        Course course = new Course();
        course.setCname("java11");
        course.setUserId(111L);
        course.setCstatus("Normal111");
        courseMapper.insert(course);
    }

    @Test
    public void findCourseDb(){
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",100L);
        wrapper.eq("cid",827574326982082561L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }


    //===================测试水平分表===========
    @Test
    void addCourse() {
        for (int i=0;i<=10;i++) {
            Course course = new Course();
            course.setCname("java"+i);
            course.setUserId(100L);
            course.setCstatus("Normal"+i);
            courseMapper.insert(course);
        }
    }

    @Test
    void findCourse() {
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("cid",827565457941725184L);
        Course course=courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

}

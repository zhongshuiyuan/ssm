package ssm;

import javax.annotation.Resource;  
  
import org.apache.log4j.Logger;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
import com.alibaba.fastjson.JSON;  
import com.cn.hnust.pojo.User;  
import com.cn.hnust.service.UserService;  
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
public class TestMybatis {  
    private static Logger logger = Logger.getLogger(TestMybatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private UserService userService;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
        User user = userService.getUserById(1);  
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    } 
    
    @Test
    public void testCache1(){
    	User user = userService.getUserById(17);
    	System.out.println(user);
    }
    
    @Test
    public void testUpdate(){
    	User user = userService.getUserById(5);
    	System.out.println("修改前的："+user);
    	user.setUsername("郑智化");
    	userService.updateUser(user);
    	System.out.println("修改后:"+userService.getUserById(5));
    }
    
    @Test
    public void testDelete(){
    	userService.deleteUser(17);
    }
}
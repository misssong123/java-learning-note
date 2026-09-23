
package org.example.springbootdemo.stardemo.web;

import com.crm.mystater.event.LogEvent;
import com.crm.mystater.properties.MyProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import  org.example.springbootdemo.stardemo.dto.User;
import javax.annotation.Resource;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller

public class BasicController {
    @Resource
    private MyProperties myProperties;
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private User user;
    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        applicationContext.publishEvent(new LogEvent("/user 被调用"));
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser() {
        return "name:"+user.getName()+" age:"+user.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }
    // http://127.0.0.1:8080/myProperties
    @RequestMapping("/myProperties")
    @ResponseBody
    public String myProperties() {
        System.out.println("myProperties: " + myProperties.getName());
        return "id:" + myProperties.getId()+ ",name: " + myProperties.getName()+",url: " + myProperties.getUrl();
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
    @RequestMapping("/isExistCompany")
    @ResponseBody
    public String isExistCompany(String oppId, String companyName, String storeName, Integer companyType,
                                 Integer productLine, Integer cityIdFor58, Integer cateTwoId,
                                 @RequestParam(defaultValue = "true") boolean checkInWhiteList,
                                 @RequestParam(defaultValue = "false") boolean checkChannelName) {
        System.out.println("oppId:" + oppId + ",companyName:" + companyName + ",storeName:" + storeName +
                ",companyType:" + companyType +",productLine:" + productLine + ",cityIdFor58:" + cityIdFor58 +
                ",cateTwoId:" + cateTwoId + ",checkInWhiteList:" + checkInWhiteList
                      + ",checkChannelName:" + checkChannelName);
        return "success";
    }
}

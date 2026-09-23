package org.example.springbootdemo.annonationdemo;

import com.example.springbootdemo.annonationdemo.interfacedemo.ILBGSaleCallAICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  @Autowired 和 @Resource 的区别
 *  @Autowired 是 Spring 框架提供的注解，用于自动装配 Bean。
 *  @Resource 是 JSR-250 规范提供的注解，也用于自动装配 Bean。
 *  区别：
 *  @Autowired 是 Spring 框架提供的注解，而 @Resource 是 JSR-250 规范提供的注解。
 *  @Autowired 默认按类型装配 Bean，而 @Resource 默认按名称装配 Bean。
 *  @Autowired 可以用于构造函数、Setter 方法和字段，而 @Resource 只能用于字段和 Setter 方法。
 */
@RestController
@RequestMapping("/test")
public class AnnotationDemo {
    @Resource
    private ILBGSaleCallAICheckService lBGSaleCallAICheckService;
    @Autowired
    private ILBGSaleCallAICheckService lbgSaleCallAICheckService;

/**
 * 处理/printName请求映射的方法
 * 该方法调用两个不同的服务类方法来打印名称
 *
 * @RequestMapping("/printName") 用于将HTTP请求映射到该方法
 * @return void 此方法不返回任何值
 */
    @RequestMapping("/printName")
    public void printName() {
    // 调用第一个服务类的printName方法
        lbgSaleCallAICheckService.printName();
    // 调用第二个服务类的printName方法
    // 注意：这里第二个服务类的变量名与第一个略有不同（大小写区别）
        lBGSaleCallAICheckService.printName();
    }


}

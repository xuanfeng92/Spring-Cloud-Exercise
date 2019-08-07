package com.xh.springcloud;

import com.xh.myrobbinrulers.RandomRule_self;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
// @RibbonClient(name = "microservicecloud-dept", configuration = RandomRule_self.class)  // 可以自定义Ribbon策略
public class DeptConsumer80_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class);
    }
}

package com.xh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.xh.springcloud"})
// @RibbonClient(name = "microservicecloud-dept", configuration = RandomRule_self.class)  // 可以自定义Ribbon策略
public class DeptConsumer88_Feign_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer88_Feign_App.class);
    }
}

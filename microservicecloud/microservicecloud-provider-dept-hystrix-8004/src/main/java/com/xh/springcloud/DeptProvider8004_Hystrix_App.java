package com.xh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaClient     // 启动后自动注入进eureka服务中
@EnableDiscoveryClient  // 服务发现
@EnableCircuitBreaker   // Hystrix标识，和@EnableHystrix 功效一样
public class DeptProvider8004_Hystrix_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8004_Hystrix_App.class);
    }
}

package com.xh.springcloud.controller;

import com.xh.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController {

    // private static final String REST_URL_PREFIX = "http://localhost:8001"; // 未使用Eureka集群和Ribbon时，写死的请求
    private static final String REST_URL_PREFIX = "http://microservicecloud-dept";  // 根据要访问指定微服务的名称（在Eureka上注册的名称spring.application.name）,而该微服务是在Eureka集群上注册了.此时客户端会从Eureka集群中注册的微服务获取请求地址(会根据负载均衡算法进行访问)

    @Autowired      // 设计一个RestTemplate的bean,该bean由@LoadBalanced时，使用的Eureka集群的负载均衡的功能
    private RestTemplate restTemplate;

    @RequestMapping(value="/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept, Boolean.class);
    }

    @RequestMapping(value="/consumer/dept/get/{id}" , method =RequestMethod.GET)
    public Dept get(@PathVariable("id") long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
    }

    @RequestMapping(value="/consumer/dept/list" ,method = RequestMethod.GET)
    public List<Dept> getList(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

}

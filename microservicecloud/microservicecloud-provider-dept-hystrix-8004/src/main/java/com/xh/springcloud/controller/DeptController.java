package com.xh.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xh.springcloud.entities.Dept;
import com.xh.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;

    @Autowired
    DiscoveryClient discoveryClient;  // 用于服务发现

    @RequestMapping(value="/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return service.add(dept);
    }

    @RequestMapping(value="/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get") // 如果这个方法异常了，执行指定方法返回数据
    public Dept get(@PathVariable("id") Long id){
        // 这里模仿微服务异常调用，导致微服务熔断
        Dept result = service.get(id);
        if(null == result){
            throw new RuntimeException("该ID： "+ id +" 没有对应的信息");
        }
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list(){
        return service.getAll();
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Object getInfo(){
        List<String> services = discoveryClient.getServices();
        System.out.println("******"+services);
        List<ServiceInstance> instances = discoveryClient.getInstances("microservicecloud-dept");
        for(ServiceInstance s: instances){
            System.out.println(s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort());
        }
        return discoveryClient;
    }

    @RequestMapping(value = "/health")
    public String getHealth(){
        return "health ok!";
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("该ID： "+ id +" 没有对应的信息").setDb_source("no this database in mysql");
    }

}

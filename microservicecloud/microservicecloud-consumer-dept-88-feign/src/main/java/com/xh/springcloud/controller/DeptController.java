package com.xh.springcloud.controller;

import com.xh.springcloud.entities.Dept;
import com.xh.springcloud.services.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController {

    // 使用Feign 方式调用
    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping(value="/consumer/dept/add")
    public boolean add(Dept dept){
        return deptClientService.add(dept);
    }

    @RequestMapping(value="/consumer/dept/get/{id}" , method =RequestMethod.GET)
    public Dept get(@PathVariable("id") long id){
        return deptClientService.get(id);
    }

    @RequestMapping(value="/consumer/dept/list" ,method = RequestMethod.GET)
    public List<Dept> getList(){
        return deptClientService.getAll();
    }

}

package com.xh.springcloud.services;

import com.xh.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 微服务降级处理，对于微服务突然不可用时，进行的特殊处理
 */
@Component  // 一定不要忘记
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id).setDname("该ID： "+ id +" 没有对应的信息，此时服务Provider已经关闭").setDb_source("no this database in mysql");
            }

            @Override
            public List<Dept> getAll() {
                return null;
            }
        };
    }
}

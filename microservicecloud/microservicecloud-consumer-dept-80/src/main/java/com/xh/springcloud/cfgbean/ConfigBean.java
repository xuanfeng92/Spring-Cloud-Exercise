package com.xh.springcloud.cfgbean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ConfigBean {
    /**
     * 相当于模仿http请求的方法，毕竟是客户端，不应该直接使用service层的东西
     * @return
     */
    @Bean
    @LoadBalanced           // spring cloud Ribbon实现负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 可以显式的覆盖默认的Ribbon负载算法（切换Ribbon负载算法）
     * @return
     */
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}

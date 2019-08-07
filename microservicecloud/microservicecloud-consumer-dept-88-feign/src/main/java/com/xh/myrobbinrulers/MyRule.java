package com.xh.myrobbinrulers;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon策略
 */
@Configuration
public class MyRule {

    @Bean
    public IRule myRule(){
        // return new RandomRule();
        // return new RoundRobinRule();
        return new RandomRule_self(5);
    }
}

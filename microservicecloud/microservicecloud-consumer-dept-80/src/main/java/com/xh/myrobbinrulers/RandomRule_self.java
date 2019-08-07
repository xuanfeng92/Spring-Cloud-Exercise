package com.xh.myrobbinrulers;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可以参考已有的Robbin策略，当前需求是：随机策略，只是每个机器使用5次
 */
public class RandomRule_self extends AbstractLoadBalancerRule {

    private AtomicInteger total = new AtomicInteger(0);
    private AtomicInteger index = new AtomicInteger(0);

    private final Integer count ;

    public RandomRule_self(Integer count) {
        if(null != count){
            this.count = count;
        }else{
            this.count = Integer.valueOf(3);
        }
    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }
                int currentIndex = index.get();
                int currentTotal = total.get();
                if(currentTotal<count){
                    server = (Server)upList.get(currentIndex);
                    total.incrementAndGet();
                }else{
                    currentIndex = this.chooseRandomInt(serverCount);
                    index.set(currentIndex);
                    total.set(0);
                    server = upList.get(currentIndex);
                }
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }
                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

}

package com.baizhi.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {
    @Bean
    @LoadBalanced    //作用：使对象具有ribbon负载均衡特性
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

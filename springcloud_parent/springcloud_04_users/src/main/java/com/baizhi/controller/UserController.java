package com.baizhi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired   //服务注册与发现的客户端对象，返回一个服务实例的列表
    private DiscoveryClient discoveryClient;

    @Autowired    //具有负载均衡的客户端对象
    private LoadBalancerClient loadBalancerClient;

    @Autowired

    private RestTemplate restTemplate;
    @GetMapping("user")
    public String invokeDemo(){
        System.out.println("user demo ...");
        //1. 调用订单服务，服务地址：http://localhost:9999/order，接收返回值
//        RestTemplate restTemplate = new RestTemplate();
//        String orderResult = restTemplate.getForObject("http://localhost:9999/order", String.class);

        //2. 使用ribbon组件+RestTemplate实现负载均衡调用 DiscoveryClient LoadBalancerClient @LoadBalance
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("ORDERS");
//        serviceInstances.forEach(serviceInstance -> {
//            log.info("服务主机:{} 服务端口:{} 服务地址:{}",serviceInstance.getHost(),serviceInstance.getPort(),serviceInstance.getUri());
//        });
//
//        String orderResult = new RestTemplate().getForObject(serviceInstances.get(0).getUri() + "/order", String.class);

        //3. 使用loadBalancerClient对象进行服务调用，这已经是负载均衡之后的结果了，默认是轮询策略
//        ServiceInstance serviceInstance = loadBalancerClient.choose("ORDERS");
//        log.info("服务主机:{} 服务端口:{} 服务地址:{}",serviceInstance.getHost(),serviceInstance.getPort(),serviceInstance.getUri());
//        String orderResult = restTemplate.getForObject(serviceInstance.getUri() + "/order", String.class);

        //4. 使用@loadBalance注解
        String orderResult = restTemplate.getForObject("http://ORDERS/order", String.class);

        log.info("调用订单服务成功：{}", orderResult);
        return orderResult;
    }
}

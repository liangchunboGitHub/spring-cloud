package com;

import config.TestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(value = "PROVIDER-USER",configuration = TestConfig.class) //启用负载
public class OrderApplication {
    @LoadBalanced  //使用负载均衡机制
    @Bean
    public RestTemplate getTemp(){
        return new RestTemplate();
    }

    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class);
    }
}

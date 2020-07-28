package com.yuxin.controller;

import com.netflix.discovery.EurekaClient;
import com.yuxin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Value("${user.url}")
    private String url;

    @RequestMapping("/order/{id}")
    public User getOrder(@PathVariable Integer id){
//        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("provider-user", false);
        return restTemplate.getForObject("http://PROVIDER-USER/user/"+id,User.class);
    }

    @RequestMapping("/test")
    public String test(){
        ServiceInstance choose = loadBalancerClient.choose("PROVIDER-USER");
         System.out.println(choose.getServiceId()+"--->"+choose.getHost()+":"+choose.getPort());
         return "111";
    }


}

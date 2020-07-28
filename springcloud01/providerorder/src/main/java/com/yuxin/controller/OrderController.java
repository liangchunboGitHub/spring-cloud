package com.yuxin.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.yuxin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${user.url}")
    private String url;

    @RequestMapping("/order/{id}")
    public User getOrder(@PathVariable Integer id){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("provider-user", false);
        return restTemplate.getForObject(nextServerFromEureka.getHomePageUrl()+"/user/"+id,User.class);
    }


}

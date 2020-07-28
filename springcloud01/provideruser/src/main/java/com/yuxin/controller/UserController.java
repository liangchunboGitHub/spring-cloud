package com.yuxin.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.yuxin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private EurekaClient eurekaClient;
    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Integer id){
        return new User(id,"张三",30);
    }

    @RequestMapping("/info")
    public String info(){
        InstanceInfo nextServerFromEureka = eurekaClient.getNextServerFromEureka("provider-user", false);
        return nextServerFromEureka.getHomePageUrl();
    }
}

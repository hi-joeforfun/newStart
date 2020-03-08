package com.dalaoyang.eureka.controller;

import com.dalaoyang.eureka.api.EurakaServeceInstance;
import com.dalaoyang.eureka.model.ServerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EurakaController {

    @Autowired
    EurakaServeceInstance eurakaServeceInstance;

    //服务注册
    @RequestMapping(value = "/heartBeat",method = RequestMethod.POST)
    public boolean heartBeat(@RequestBody ServerInstance instance){
        return eurakaServeceInstance.heartBeat(instance);
    }

    //服务发现
    @RequestMapping(value = "/getServer",method = RequestMethod.POST)
    public List<ServerInstance> getServer(String serverId){
        return eurakaServeceInstance.getServer(serverId);
    }

}

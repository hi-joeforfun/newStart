package com.dalaoyang.eureka.api;

import com.dalaoyang.eureka.model.ServerInstance;

import java.util.List;
import java.util.Map;

public interface EurakaServeceInstance {

    //心跳
    boolean heartBeat(ServerInstance serverInstance);

    //服务注册，更新，续约
    String renew(ServerInstance serverInstance);

    //获取在线服务
    List<ServerInstance> getServer(String serverId);

    //获取所有服务
    Map<String,Object> getAllServer(String serverId);

}

package com.dalaoyang.eureka.api;

import com.dalaoyang.eureka.model.ServerInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EurakaServeceInstanceImp implements EurakaServeceInstance {

    Map<String , Object> serverMap = new ConcurrentHashMap<>();

    @Override
    public boolean heartBeat(ServerInstance instance) {
        List<ServerInstance> instances =  (List<ServerInstance>)serverMap.get(instance.getServerName());
        if(instances == null){
            //上线新的服务
            addInstance( instance , new ArrayList<>());
        }
        return false;
    }

    private void addInstance(ServerInstance serverInstance , List<ServerInstance> instances){
        serverInstance.setStatus("UP");
        serverInstance.setIsDirtyWithTime(System.currentTimeMillis());
        serverMap.put(serverInstance.getServerName(),instances);
    }

    @Override
    public String renew(ServerInstance serverInstance) {
        return null;
    }

    @Override
    public List<ServerInstance> getServer(String serverId) {
        return null;
    }
}

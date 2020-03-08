package com.dalaoyang.eureka;

import com.dalaoyang.eureka.api.EurakaServeceInstance;
import com.dalaoyang.eureka.model.ServerInstance;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class EurakaServeceInstanceImp implements EurakaServeceInstance {

    Map<String , Object> serverMap = new ConcurrentHashMap<>();

    @Override
    public boolean heartBeat(ServerInstance instance) {
        List<ServerInstance> instances =  (List<ServerInstance>)serverMap.get(instance.getServerName());
//        if(instances == null){
//            //上线新的服务
//            addInstance( instance , new ArrayList<>());
//        }else {
            //完全没有的 ，也就是注册
            //原来有这个服务，但是现在又加一个
            //心跳 就是修改一下时间
            switch(renew(instance)){
                case "404":
                    addInstance( instance , new ArrayList<>());
                case "303"://存在服务，增加一个新的
                    addInstance( instance , instances);
                case "200"://刷新，干掉了旧服务 现在去增加新的服务
                    addInstance(instance,instances);
                    break;
            }
//        }
        return true;
    }

    private void addInstance(ServerInstance serverInstance , List<ServerInstance> instances){
        serverInstance.setStatus("UP");
        serverInstance.setIsDirtyWithTime(System.currentTimeMillis());
        instances.add(serverInstance);
        serverMap.put(serverInstance.getServerName(),instances);
    }

    @Override
    public String renew(ServerInstance instance) {
        int count = 0;
        String code ="404";
        if(serverMap.containsKey(instance.getServerName())){
            //拿到该服务名下的 所有实例服务
            List<ServerInstance> instances =  (List<ServerInstance>)serverMap.get(instance.getServerName());
            if(instances.size()!=0){
                for(ServerInstance oldInstance : instances){
                    //判断两个同名服务是重复注册，还是新示例 ？  ip相同，端口不同 -- 》》心跳刷新
                    if(oldInstance.getHost().equals(instance.getHost())
                            && oldInstance.getPort().equals(instance.getPort()) ){
                        instances.remove(oldInstance);
                        count++;
                        break;
                    }
                }
            }
            code = count > 0 ? "200":"303";
        }
        return code;
    }

    ScheduledExecutorService removeScheduled = Executors.newScheduledThreadPool(2);


    @PostConstruct
    public void renewServer(){
        removeScheduled.scheduleAtFixedRate(()->{
            for(String key : serverMap.keySet() ){
                List<ServerInstance> instances =  (List<ServerInstance>)serverMap.get(key);
                for(ServerInstance instance:instances){
                    //大于90秒没有心跳就算 挂了服务
                    if( System.currentTimeMillis() - instance.getIsDirtyWithTime() > 90000 ){
                        instance.setStatus("DOWN");
                    }
                }
            }
        } ,30,30, TimeUnit.SECONDS );
    }


    @Override
    public List<ServerInstance> getServer(String serverId) {
        //开放服务

        return null;
    }

    @Override
    public Map<String,Object> getAllServer(String serverId) {
        return serverMap;
    }
}

package com.dalaoyang.eureka.model;

public class ServerInstance {

    private String serverName;//服务名

    private String host;//ip地址

    private String port;//端口号

    private String status;//状态码，定义 上下线

    private Long isDirtyWithTime;//最后一次注册时间

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIsDirtyWithTime() {
        return isDirtyWithTime;
    }

    public void setIsDirtyWithTime(Long isDirtyWithTime) {
        this.isDirtyWithTime = isDirtyWithTime;
    }
}

package com.dalaoyang.proxy.aop;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class IocContainer {
    //简化版的bean定义map
    private Map<String,Class<?>> beanDefinitionMap = new HashMap<>();

    //bean容器
    private Map<String,Object> beanMap = new HashMap<>();

    //很简单版的 （应为一个list 支持多切面） List<Aspect>
    private Aspect aspect;

    public void addBeanDefinition(String beanName,Class<?> beanClass){
        this.beanDefinitionMap.put(beanName,beanClass);
    }

    public Object getBean(String beanName) throws Exception{
        Object bean = beanMap.get(beanName);

        if(bean ==null){
            bean = this.createInstance(beanName);
            bean = this.proxyEnhance(bean);
            this.beanMap.put(beanName,bean);
        }
        return  bean;
    }

    private Object proxyEnhance(Object bean) {
        if(this.aspect != null && this.aspect.getPointcut().matchClass(bean.getClass())){
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(),bean.getClass().getInterfaces(),
                    new AopInvokeHandle(this.aspect,bean));
        }
        return bean;
    }

    private Object createInstance(String beanName) throws Exception{
        return this.beanDefinitionMap.get(beanName).newInstance();
    }


    public Map<String, Class<?>> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public void setBeanDefinitionMap(Map<String, Class<?>> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }

    public Map<String, Object> getBeanMap() {
        return beanMap;
    }

    public void setBeanMap(Map<String, Object> beanMap) {
        this.beanMap = beanMap;
    }

    public Aspect getAspect() {
        return aspect;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }
}

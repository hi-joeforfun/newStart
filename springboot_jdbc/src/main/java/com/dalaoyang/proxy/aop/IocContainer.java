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

    //bean的定义
    public void addBeanDefinition(String beanName,Class<?> beanClass){
        this.beanDefinitionMap.put(beanName,beanClass);
    }

    public Object getBean(String beanName) throws Exception{
        //获取bean的实体对象
        Object bean = beanMap.get(beanName);
        //如果是空就，就去加载该实体对象，该过程中就会涉及到 是否做增强操作
        if(bean ==null){
            bean = this.createInstance(beanName);//加载该bean定义的 实体
            bean = this.proxyEnhance(bean);
            this.beanMap.put(beanName,bean);
        }
        return  bean;
    }

    private Object proxyEnhance(Object bean) {
        if(this.aspect != null  // 否被存在切面
                && this.aspect.getPointcut().matchClass(bean.getClass())){// 切面是否被切中 该方法
            //满足上面2点 进行增强操作
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

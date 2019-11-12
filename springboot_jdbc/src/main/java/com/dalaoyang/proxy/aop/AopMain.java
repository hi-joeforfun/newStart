package com.dalaoyang.proxy.aop;

import com.dalaoyang.service.Luluse;
import com.dalaoyang.service.imp.LuluseImp;

public class AopMain {

    public static void main(String[] args) throws Exception{
        IocContainer ioc = new  IocContainer();
        ioc.addBeanDefinition("luluse", LuluseImp.class);

        Pointcut pointcut = new Pointcut("com\\.dalaoyang\\.service..*",".*luluse");

        Aspect aspect = new Aspect(pointcut,new TimeAdvice());

        ioc.setAspect(aspect);
//        System.out.println(ioc.getBeanDefinitionMap().get("luluse"));
//        Luluse luluse2 = LuluseImp.class.newInstance();
        Luluse luluse = (Luluse) ioc.getBean("luluse");
        luluse.luluse("joe");


    }
}

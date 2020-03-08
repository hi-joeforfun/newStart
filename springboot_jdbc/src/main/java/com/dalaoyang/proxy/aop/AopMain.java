package com.dalaoyang.proxy.aop;

import com.dalaoyang.service.Luluse;
import com.dalaoyang.service.imp.LuluseImp;

public class AopMain {

    public static void main(String[] args) throws Exception{
        IocContainer ioc = new IocContainer();
        ioc.addBeanDefinition("luluse", LuluseImp.class);

        //切点 包括目标类和目标方法，用正则表达式来进行判断
        Pointcut pointcut = new Pointcut("com\\.dalaoyang\\.service..*",".*luluse");

        //切面包括 切点和目标方法
        Aspect aspect = new Aspect(pointcut,new TimeAdvice());

        ioc.setAspect(aspect);
//        System.out.println(ioc.getBeanDefinitionMap().get("luluse"));
//        Luluse luluse2 = LuluseImp.class.newInstance();
        Luluse luluse = (Luluse) ioc.getBean("luluse");
        luluse.luluse("joe");

//        LuluseImp luluse = new LuluseImp();
//        TimeAdvice timeAdvice = new TimeAdvice();
////        Object[] a = new Object[3];
//        timeAdvice.invoke(luluse,luluse.getClass().getMethod("luluse",String.class),null);

    }
}

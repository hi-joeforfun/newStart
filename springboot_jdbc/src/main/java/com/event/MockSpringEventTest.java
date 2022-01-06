package com.event;

import com.event.context.ContextDestroyEvent;
import com.event.context.ContextRunningEvent;
import com.event.context.ContextStartEvent;
import com.event.listener.ContextDestroyEventListener;
import com.event.listener.ContextRunningEventListener;
import com.event.listener.ContextStartEventListener;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

public class MockSpringEventTest {

    @Test
    public void testContextLifecycleEventInSync() {
        // 新建SimpleApplicationEventMulticaster对象，并添加容器生命周期监听器
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());
        // 发射容器启动事件ContextStartEvent
        eventMulticaster.multicastEvent(new ContextStartEvent());
        // 发射容器正在运行事件ContextRunningEvent
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        // 发射容器正在运行事件ContextDestroyEvent
        eventMulticaster.multicastEvent(new ContextDestroyEvent());
    }

    @Test
    public void testContextLifecycleEventInAsync() throws InterruptedException {
        // 新建SimpleApplicationEventMulticaster对象，并添加容器生命周期监听器
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());

        ((SimpleApplicationEventMulticaster) eventMulticaster).setAsync(true);

        // 发射容器启动事件ContextStartEvent
        eventMulticaster.multicastEvent(new ContextStartEvent());
        // 发射容器正在运行事件ContextRunningEvent
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        // 发射容器正在运行事件ContextDestroyEvent
        eventMulticaster.multicastEvent(new ContextDestroyEvent());
        // 这里没明白在没有用CountDownLatch的情况下为何主线程退出，非后台线程的子线程也会退出？？？为了测试，所有先用CountDownLatch锁住main线程先
        // 经过测试，原来是因为用了junit的方法，test方法线程退出后，test方法线程产生的非后台线程也随之退出，而下面的main方法启动的非后台线程则不会
        // TODO 这是为什么呢？？？难道是A子线程（非main线程）启动的B子线程会随着A子线程退出而退出？还没验证
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }

    public static void main(String[] args) throws InterruptedException {
        // 新建SimpleApplicationEventMulticaster对象，并添加容器生命周期监听器
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());

        ((SimpleApplicationEventMulticaster) eventMulticaster).setAsync(true);

        // 发射容器启动事件ContextStartEvent
        eventMulticaster.multicastEvent(new ContextStartEvent());
        // 发射容器正在运行事件ContextRunningEvent
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        // 发射容器正在运行事件ContextDestroyEvent
        eventMulticaster.multicastEvent(new ContextDestroyEvent());

    }
}

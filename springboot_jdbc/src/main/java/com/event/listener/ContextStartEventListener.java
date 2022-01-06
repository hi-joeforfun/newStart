package com.event.listener;

import com.event.context.AbstractContextEvent;
import com.event.context.ContextStartEvent;
import com.event.listener.ContextListener;

public class ContextStartEventListener implements ContextListener<AbstractContextEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("容器启动。。。，启动时间为：" + event.getTimestamp());
        }
    }
}
package com.event.listener;

import com.event.context.AbstractContextEvent;
import com.event.context.ContextDestroyEvent;
import com.event.listener.ContextListener;

public class ContextDestroyEventListener implements ContextListener<AbstractContextEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextDestroyEvent) {
            System.out.println("容器销毁。。。，销毁时间为：" + event.getTimestamp());
        }
    }
}
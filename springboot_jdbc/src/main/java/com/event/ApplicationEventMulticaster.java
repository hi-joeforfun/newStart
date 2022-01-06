package com.event;

import com.event.context.AbstractContextEvent;
import com.event.listener.ContextListener;

public interface ApplicationEventMulticaster {
    void addContextListener(ContextListener<?> listener);

    void removeContextListener(ContextListener<?> listener);

    void removeAllListeners();

    void multicastEvent(AbstractContextEvent event);

}
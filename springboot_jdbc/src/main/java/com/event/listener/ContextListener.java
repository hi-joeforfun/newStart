package com.event.listener;


import com.event.context.AbstractContextEvent;

public interface ContextListener<T extends AbstractContextEvent> extends EventListener {
    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(T event);
}


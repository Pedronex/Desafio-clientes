package com.nexdev.jaimedesafio.seed;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public interface Seed {

    @EventListener
    void seed(ContextRefreshedEvent event);

    void create() throws Exception;
}

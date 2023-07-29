package com.nexdev.jaimedesafio.seed;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public interface Seed {

    // Método usado como um EventListener para ouvir o evento ContextRefreshedEvent
    @EventListener
    void seed(ContextRefreshedEvent event);

    // Método para criar dados iniciais ou populares em algum lugar do aplicativo
    void create() throws Exception;
}

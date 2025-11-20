package com.empresa.rh.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SwaggerStartupLogger {

    @EventListener(ApplicationReadyEvent.class)
    public void logSwaggerUrl() {
        System.out.println("Swagger UI disponível em:");
        System.out.println("http://localhost:8080/swagger-ui/index.html");

        System.out.println("Front-end");
        System.out.println("http://localhost:8080/index.html");
    }
}

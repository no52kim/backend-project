package com.musinsa.backendproject.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Slf4j
public class H2ServerConfig {
    private Server webServer;

    @EventListener
    public void start(ContextRefreshedEvent event) throws SQLException {
        this.webServer = Server.createWebServer("-webPort", "8089", "-tcpAllowOthers").start();
    }

    @EventListener
    public void stop(ContextClosedEvent event){
        this.webServer.stop();

    }

}
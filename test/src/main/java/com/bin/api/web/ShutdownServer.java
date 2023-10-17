package com.bin.api.web;


import io.undertow.Undertow;
import io.undertow.server.ConnectorStatistics;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 处理完请求后关闭服务
 */
@Component
public class ShutdownServer implements ApplicationListener<ContextClosedEvent> {

    private ShutdownServerWrapper shutdownServerWrapper;
    private ServletWebServerApplicationContext context;

    public ShutdownServer() {
    }

    public ShutdownServer(ShutdownServerWrapper shutdownServerWrapper, ServletWebServerApplicationContext context) {
        this.shutdownServerWrapper = shutdownServerWrapper;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        try {
            shutdownServerWrapper.getGracefulShutdownHandler().shutdown();
            UndertowServletWebServer webServer = (UndertowServletWebServer) context.getWebServer();
            Field field = webServer.getClass().getDeclaredField("undertow");
            field.setAccessible(true);
            Undertow undertow = (Undertow) field.get(webServer);
            List<Undertow.ListenerInfo> listenerInfo = undertow.getListenerInfo();
            Undertow.ListenerInfo listener = listenerInfo.get(0);
            ConnectorStatistics connectorStatistics = listener.getConnectorStatistics();
            while (connectorStatistics.getActiveConnections() > 0) {
            }
        } catch (Exception e) {
            // Application Shutdown
        }
    }


}

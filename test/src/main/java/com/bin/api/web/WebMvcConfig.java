package com.bin.api.web;


import com.bin.api.web.base.DisposeRequest;
import com.bin.webase.core.web.DisposeApiRequest;
import io.undertow.UndertowOptions;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    private ShutdownServerWrapper shutdownServerWrapper;

    public WebMvcConfig(ShutdownServerWrapper shutdownServerWrapper) {
        this.shutdownServerWrapper = shutdownServerWrapper;
    }

    @Bean
    public UndertowServletWebServerFactory servletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addOuterHandlerChainWrapper(shutdownServerWrapper));
        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_STATISTICS, true));
        return factory;
    }

    @Bean
    public DisposeApiRequest DisposeApiRequest() {
        return new DisposeApiRequest(false, new DisposeRequest());
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST")
                .allowedHeaders("*")
                .allowCredentials(true).maxAge(3600);

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}

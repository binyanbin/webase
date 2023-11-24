package com.bin.api.web.base;


import com.bin.webase.core.context.WebaseContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitWe implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        WebaseContext.init(ApplicationContextHolder.getSpringContext(), "com.bin.api");
    }
}

package com.bin.api.web.base;


import com.bin.api.web.base.ApplicationContextHolder;
import com.bin.webase.core.context.WeContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitWe implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        WeContext.init(ApplicationContextHolder.getSpringContext(), "com.bin.api");
    }
}

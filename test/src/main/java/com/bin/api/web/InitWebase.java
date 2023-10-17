package com.bin.api.web;


import com.bin.webase.core.context.WeContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitWebase implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        WeContext.init(ApplicationContextHolder.getSpringContext(), "com.bin.api");
    }


}

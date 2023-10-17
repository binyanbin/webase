package com.bin.api.web;


import com.bin.webase.core.context.WeContext;
import com.bin.webase.exception.ApplicationException;
import com.bin.webase.exception.ErrorCode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class InitRegistry implements CommandLineRunner {

    public static final String DOMAIN_PACKAGES = "domain.packages";

    @Override
    public void run(String... args) throws Exception {
        String value = ApplicationContextHolder.getEnvironmentProperty(DOMAIN_PACKAGES);
        if (!StringUtils.hasText(value)) {
            throw new ApplicationException(ErrorCode.bootFail);
        }
        String[] packages = value.split(",");
        if (packages.length == 0) {
            return;
        }
        WeContext.init(ApplicationContextHolder.getSpringContext(),packages);
    }


}

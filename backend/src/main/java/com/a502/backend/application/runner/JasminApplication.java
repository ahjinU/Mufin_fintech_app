package com.a502.backend.application.runner;

import com.a502.backend.application.facade.InitFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JasminApplication implements ApplicationRunner {
    private final InitFacade initFacade;

    @Override
    public void run(ApplicationArguments args)throws IOException{
        initFacade.run();
    }
}

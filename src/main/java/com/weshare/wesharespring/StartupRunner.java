package com.weshare.wesharespring;

import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StartupRunner implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(String... strings) throws Exception {

        logger.info("StartupRunner Starting...");
    }
}
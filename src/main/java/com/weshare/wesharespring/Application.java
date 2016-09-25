package com.weshare.wesharespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties
        .EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.annotation.method.configuration
        .EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.stream.Stream;

@SpringBootApplication
@EnableWebMvc
@EnableRetry
@EnableConfigurationProperties
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Bean
    CommandLineRunner clr() {
        return args -> {
            Stream.of("hello", "world").forEach(
                name -> logger.info("Runner is running with {}", name)
            );
        };
    }


    public static void main(String[] args) {

        logger.info("Starting wesharespring Restful API server...");

        final ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        /*
        logger.info("Listing the beans provided by Spring Boot:");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (final String beanName : beanNames) {
            System.out.println(beanName);
        }
        */
    }

    @Bean
    public StartupRunner startupRunner() {
        return new StartupRunner();
    }

}
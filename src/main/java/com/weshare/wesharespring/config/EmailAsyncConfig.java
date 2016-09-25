package com.weshare.wesharespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class EmailAsyncConfig {

    @Bean(name = "emailAsyncExecutor")
    public Executor emailAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(50);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setThreadNamePrefix("WeShareEmailAsyncExecutor-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setBeanName("emailAsyncExecutor");
        taskExecutor.initialize();
        return taskExecutor;
    }
}

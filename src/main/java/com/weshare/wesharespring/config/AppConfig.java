package com.weshare.wesharespring.config;

import com.weshare.wesharespring.common.client.AwsS3Client;
import com.weshare.wesharespring.common.filter.CorsFilter;
import com.weshare.wesharespring.jdbi.dao.*;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class AppConfig {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    @Autowired
    private DBI appDbi;

    /**
    /* Filter
    */
    @Bean
    public FilterRegistrationBean corsFilter() {
        final FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        final CorsFilter corsFilter = new CorsFilter();
        beanFactory.autowireBean(corsFilter);
        filterRegistration.setFilter(corsFilter);
        filterRegistration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
    /**
     * Client
     */
    @Bean
    public AwsS3Client awsClient() { return new AwsS3Client(); }

    /**
     * DAO
     */
    @Bean
    public UserDao userDao() { return this.appDbi.onDemand(UserDao.class); }
    @Bean
    public TokenDao tokenDao() { return this.appDbi.onDemand(TokenDao.class); }
    @Bean
    public ProfileDao profileDao() { return this.appDbi.onDemand(ProfileDao.class); }
    @Bean
    public TopicDao topicDao() { return this.appDbi.onDemand(TopicDao.class); }
    @Bean
    public AppointmentDao appointmentDao() { return this.appDbi.onDemand(AppointmentDao.class); }
    @Bean
    public EmailDao emailDao() { return this.appDbi.onDemand(EmailDao.class); }
    @Bean
    public MailerDao mailerDao() { return this.appDbi.onDemand(MailerDao.class); }
}

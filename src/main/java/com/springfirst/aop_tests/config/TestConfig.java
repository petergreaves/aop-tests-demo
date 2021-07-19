package com.springfirst.aop_tests.config;

import com.springfirst.aop_tests.aspects.CustomerManagerServiceAOPLogging;
import com.springfirst.aop_tests.repository.CustomerRepository;
import com.springfirst.aop_tests.services.CustomerManagerService;
import com.springfirst.aop_tests.services.CustomerManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TestConfig {


    @Bean
    public CustomerManagerService customerManagerService(@Autowired CustomerRepository customerRepository) {

        return new CustomerManagerServiceImpl(customerRepository);
    }

    @Bean
    public CustomerManagerServiceAOPLogging aopLogger(){

        return new CustomerManagerServiceAOPLogging();
    }


}

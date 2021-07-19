package com.springfirst.aop_tests.aspects;

import com.springfirst.aop_tests.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class CustomerManagerServiceAOPLogging {

    private long addExecutionTime = 0L;
    private Long newCustomerID;

//    @Before("execution(public * com.springfirst.aop_tests.services.CustomerManagerService.addCustomer(com.springfirst.aop_tests.domain.Customer))")
//    public void doPreAddCustomerLog(JoinPoint joinPoint) {
//
//        log.info("About to add customer with args " + Arrays.toString(joinPoint.getArgs()));
//        addExecutionTime = System.currentTimeMillis();
//    }



    // the pointcut definition lets us provider customer via args(customer) ...
    //  @Pointcut("execution(public * com.springfirst.aop_tests.services.CustomerManagerService+.addCustomer(com.springfirst.aop_tests.domain.Customer)) " +
    //            "&& args(customer)")

    @Before("com.springfirst.aop_tests.aspects.PointcutContainer.doPreAddCustomerLogWithPointcut(customer)")
    public void exec(Customer customer) {
        addExecutionTime = System.currentTimeMillis();
        log.info("Via a PC,  About to add new customer {} ", customer.getFirstName() + " " + customer.getLastName());

    }

    @Before("com.springfirst.aop_tests.aspects.PointcutContainer.doAnyOp())")
    public void startup() {
        log.info("Timer reset...");
        addExecutionTime = System.currentTimeMillis();
    }


    @AfterReturning(pointcut = "execution(public * com.springfirst.aop_tests.services.CustomerManagerService.addCustomer(..))", returning = "result")
    public void doPostAddCustomerLogWithReturn(JoinPoint joinPoint, Object result) {

        Customer c = (Customer)result;

        log.info("Added new customer with customer id {},  execution time (ms) was {}",c.getCustomerNumber(),
                (System.currentTimeMillis() - addExecutionTime));


    }


    // c is the list returned by the target class call..
    // PCD doesnt need to specify it, because its the needs of the particular advice that determine if it is needed to be
    // bound, as it is here.
    @AfterReturning(value="com.springfirst.aop_tests.aspects.PointcutContainer.doGetCustomerListCustomerLog()", returning = "c")
    public void doListCustomersLog(List<Customer> c) {

        log.info("{} customers listed in {} ms.",c.size(), System.currentTimeMillis() - addExecutionTime);
    }



}

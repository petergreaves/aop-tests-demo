package com.springfirst.aop_tests.aspects;

import com.springfirst.aop_tests.domain.Customer;
import org.aspectj.lang.annotation.Pointcut;

public class PointcutContainer {

    @Pointcut(
            "execution(public * com.springfirst.aop_tests.services.CustomerManagerService+.addCustomer(com.springfirst.aop_tests.domain.Customer)) " +
                    "&& args(customer)")
    public void doPreAddCustomerLogWithPointcut(Customer customer) {    //bounds from args
    }

    @Pointcut(
            "execution(public * com.springfirst.aop_tests.services.CustomerManagerService+.get*())")
    public void doGetCustomerListCustomerLog() {
    }

    @Pointcut(
            "execution(public * com.springfirst.aop_tests.services.CustomerManagerService+.*(..))")
    public void doAnyOp() {
    }
}

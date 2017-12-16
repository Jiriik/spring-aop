package cz.vlasimsky.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    // advices for logging

    @Pointcut("execution(* cz.vlasimsky.aop.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* cz.vlasimsky.aop.dao.*.get*(..))")
    private void forGetters() {}

    @Pointcut("execution(* cz.vlasimsky.aop.dao.*.set*(..))")
    private void forSetters() {}


    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice");
    }

    @Before("forDaoPackage() && !(forGetters() || forSetters())")
    public void performApiAnalytics() {
        System.out.println("\n====>>> Executing @Before advice 22222");
    }

}

package cz.vlasimsky.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    // advices for logging

    @Before("execution(* cz.vlasimsky.aop.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice");
    }

}

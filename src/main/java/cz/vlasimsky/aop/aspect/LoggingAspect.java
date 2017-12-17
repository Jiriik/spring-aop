package cz.vlasimsky.aop.aspect;

import cz.vlasimsky.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
public class LoggingAspect {
    // advices for logging

    @Before("cz.vlasimsky.aop.aspect.AopExpressions.forDaoPackageNoGetSetMethods()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=> 1 Executing @Before advice");

        // display signature
        Signature signature = joinPoint.getSignature();
        System.out.println("signature = " + signature);

        // iterate over params
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            System.out.println("arg = " + arg);
            if (arg instanceof Account) {
                Account account = (Account) arg;
                System.out.println("(Account) arg = " + account.getName() + " " + account.getLevel());
            }
        }


    }

    @AfterReturning(
            pointcut = "execution(* cz.vlasimsky.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("method = " + method);

        System.out.println("result = " + result);
    }


}

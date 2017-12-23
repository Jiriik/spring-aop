package cz.vlasimsky.aop.aspect;

import cz.vlasimsky.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
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

        convertAccountToUpperCase(result);
    }

    @AfterThrowing(pointcut = "execution(* cz.vlasimsky.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "throwable")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("method = " + joinPoint.getSignature().toShortString());
        System.out.println("throwable = " + throwable);
    }

    @After("execution(* cz.vlasimsky.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        System.out.println("After Finally method = " + joinPoint.getSignature().toShortString());
    }


    @Around("execution(* cz.vlasimsky.aop.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Executing @Around method = " + proceedingJoinPoint.getSignature().toShortString());
        Long t1 = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long t2 = System.currentTimeMillis();
        System.out.println("Execution time " + (t2 - t1) + " ms");
        return result;
    }

    private void convertAccountToUpperCase(List<Account> result) {
        result.forEach(val -> val.setName(val.getName().toUpperCase()));
    }


}

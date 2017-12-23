package cz.vlasimsky.aop.aspect;

import cz.vlasimsky.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(3)
public class LoggingAspect {
    // advices for logging

    Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Before("cz.vlasimsky.aop.aspect.AopExpressions.forDaoPackageNoGetSetMethods()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        LOGGER.info("\n=> 1 Executing @Before advice");

        // display signature
        Signature signature = joinPoint.getSignature();
        LOGGER.info("signature = " + signature);

        // iterate over params
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            LOGGER.info("arg = " + arg);
            if (arg instanceof Account) {
                Account account = (Account) arg;
                LOGGER.info("(Account) arg = " + account.getName() + " " + account.getLevel());
            }
        }


    }

    @AfterReturning(
            pointcut = "execution(* cz.vlasimsky.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        LOGGER.info("method = " + method);

        LOGGER.info("result = " + result);

        convertAccountToUpperCase(result);
    }

    @AfterThrowing(pointcut = "execution(* cz.vlasimsky.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "throwable")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable throwable) {
        LOGGER.info("method = " + joinPoint.getSignature().toShortString());
        LOGGER.info("throwable = " + throwable);
    }

    @After("execution(* cz.vlasimsky.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        LOGGER.info("After Finally method = " + joinPoint.getSignature().toShortString());
    }


    @Around("execution(* cz.vlasimsky.aop.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("Executing @Around method = " + proceedingJoinPoint.getSignature().toShortString());
        Long t1 = System.currentTimeMillis();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (RuntimeException e) {
            LOGGER.warning("EXCEPTION AS HELL");
            throw e;
        }
        long t2 = System.currentTimeMillis();
        LOGGER.info("Execution time " + (t2 - t1) + " ms");
        return result;
    }

    private void convertAccountToUpperCase(List<Account> result) {
        result.forEach(val -> val.setName(val.getName().toUpperCase()));
    }


}

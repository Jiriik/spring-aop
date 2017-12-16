package cz.vlasimsky.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {
    @Pointcut("execution(* cz.vlasimsky.aop.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* cz.vlasimsky.aop.dao.*.get*(..))")
    public void forGetters() {}

    @Pointcut("execution(* cz.vlasimsky.aop.dao.*.set*(..))")
    public void forSetters() {}

    @Pointcut("forDaoPackage() && !(forGetters() || forSetters())")
    public void forDaoPackageNoGetSetMethods() {}
}

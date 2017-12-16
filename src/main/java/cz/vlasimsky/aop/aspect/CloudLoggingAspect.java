package cz.vlasimsky.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CloudLoggingAspect {
    @Before("cz.vlasimsky.aop.aspect.AopExpressions.forDaoPackageNoGetSetMethods()")
    public void logToCloudAsync() {
        System.out.println("\n===>>> 3 logging to cloud async");
    }
}

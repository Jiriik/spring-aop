package cz.vlasimsky.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiAnalyticsAspect {

    @Before("cz.vlasimsky.aop.aspect.AopExpressions.forDaoPackageNoGetSetMethods()")
    public void performApiAnalytics() {
        System.out.println("\n==>> 2 performing api analytics");
    }


}

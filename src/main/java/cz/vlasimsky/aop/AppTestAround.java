package cz.vlasimsky.aop;

import cz.vlasimsky.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTestAround {
    public static void main(String[] args) {
        // read spring conf
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // get bean from container
        TrafficFortuneService trafficFortuneService = ctx.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("Calling fortune service");

        String data = trafficFortuneService.getFortune();
        System.out.println("My fortune is " + data);
        // close the context
        ctx.close();


    }
}

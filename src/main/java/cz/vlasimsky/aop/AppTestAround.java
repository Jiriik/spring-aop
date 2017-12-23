package cz.vlasimsky.aop;

import cz.vlasimsky.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AppTestAround {

    private static Logger LOGGER = Logger.getLogger(AppTestAround.class.getName());

    public static void main(String[] args) {
        // read spring conf
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // get bean from container
        TrafficFortuneService trafficFortuneService = ctx.getBean("trafficFortuneService", TrafficFortuneService.class);

        LOGGER.info("Calling fortune service");

        String data = trafficFortuneService.getFortune();
        LOGGER.info("My fortune is " + data);
        // close the context
        ctx.close();


    }
}

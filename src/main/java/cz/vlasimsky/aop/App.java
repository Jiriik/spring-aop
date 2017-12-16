package cz.vlasimsky.aop;

import cz.vlasimsky.aop.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // read spring conf
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // get bean from container
        AccountDAO accountDAO = ctx.getBean("accountDAO", AccountDAO.class);

        // call the business method
        accountDAO.addAccount();

        // close the context
        ctx.close();


    }
}

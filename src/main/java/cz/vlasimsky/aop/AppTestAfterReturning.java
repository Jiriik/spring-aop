package cz.vlasimsky.aop;

import cz.vlasimsky.aop.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AppTestAfterReturning {
    public static void main(String[] args) {
        // read spring conf
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // get bean from container
        AccountDAO accountDAO = ctx.getBean("accountDAO", AccountDAO.class);

        List<Account> accounts = accountDAO.findAccounts();
        System.out.println("accounts = " + accounts);

        // close the context
        ctx.close();


    }
}

package cz.vlasimsky.aop;

import cz.vlasimsky.aop.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AppTestAfterFinally {
    public static void main(String[] args) {
        // read spring conf
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // get bean from container
        AccountDAO accountDAO = ctx.getBean("accountDAO", AccountDAO.class);

        try {
            List<Account> accounts = accountDAO.findAccounts(true);
            System.out.println("accounts = " + accounts);
        } catch (Exception e) {
            System.out.println("caught exception = " + e);
        }


        // close the context
        ctx.close();


    }
}

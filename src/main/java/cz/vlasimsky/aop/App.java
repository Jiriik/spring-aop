package cz.vlasimsky.aop;

import cz.vlasimsky.aop.dao.AccountDAO;
import cz.vlasimsky.aop.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // read spring conf
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // get bean from container
        AccountDAO accountDAO = ctx.getBean("accountDAO", AccountDAO.class);
        MembershipDAO membershipDAO = ctx.getBean("membershipDAO", MembershipDAO.class);

        // call the business method
        Account account = new Account();
        account.setLevel("234");
        account.setName("Account 1");
        accountDAO.addAccount(account, false);
        accountDAO.getName();
        accountDAO.getServiceCode();
        membershipDAO.addAccount();
        membershipDAO.goSleep();
        System.out.println("Again");
        accountDAO.addAccount(account, false);
        accountDAO.doWork();
        membershipDAO.addAccount();

        // close the context
        ctx.close();


    }
}

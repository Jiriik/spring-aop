package cz.vlasimsky.aop.dao;

import cz.vlasimsky.aop.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {


    public void addAccount(Account account, boolean vip) {
        System.out.println(getClass() + ": Doing my db work: adding account");
    }

    public void doWork() {
        System.out.println(getClass() + ": Doing my db work: doWork");
    }
}

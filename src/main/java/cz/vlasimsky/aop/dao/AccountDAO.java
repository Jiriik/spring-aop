package cz.vlasimsky.aop.dao;

import cz.vlasimsky.aop.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    public List<Account> findAccounts(boolean tripWire) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            accounts.add(new Account("Account " + i, Integer.toString(i)));
        }
        if (tripWire) {
            throw new IllegalArgumentException("Simulated exception");
        }
        return accounts;
    }

    public void addAccount(Account account, boolean vip) {
        System.out.println(getClass() + ": Doing my db work: adding account");
        this.name = account.getName();
        this.serviceCode = account.getLevel();
    }

    public void doWork() {
        System.out.println(getClass() + ": Doing my db work: doWork");
    }

    public String getName() {
        System.out.println("getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("getCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("setCode");
        this.serviceCode = serviceCode;
    }

}

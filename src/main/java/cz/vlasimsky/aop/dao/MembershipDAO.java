package cz.vlasimsky.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
    public boolean addAccount() {
        System.out.println(getClass() + ": Doing my db work: adding account");
        return false;
    }

    public void goSleep(){
        System.out.println("going to sleep");
    }
}

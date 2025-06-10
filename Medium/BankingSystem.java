package BankingSystemBasicJava.Medium;

import java.util.ArrayList;
import java.util.List;

public class BankingSystem {
    static List<Account>accounts = new ArrayList<>() ;
    public static void main(String[] args) {
        Account ac1 = new Account("p1", "m1", "l1", "ahem", 0);
        Account ac2 = new Account("p2", "m2", "l2", "brc", 2, 2340);
        accounts.add(ac1);
        accounts.add(ac2);

        showAccounts();
    }

    static public void showAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

}


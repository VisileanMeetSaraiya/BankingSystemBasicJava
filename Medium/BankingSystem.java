package BankingSystemBasicJava.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingSystem {

    static List<Account> accounts = new ArrayList<>();
    static boolean accIds[] = new boolean[200];
    static double totalBankBalance = 0.0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Account ac1 = new Account("p1", "m1", "l1", "ahem", 0);
        // Account ac2 = new Account("p2", "m2", "l2", "brc", 2, 2340);
        // accounts.add(ac1);
        // accounts.add(ac2);
        System.out.println("Bank's initial Balance :-> " + totalBankBalance);

        System.out.println("Enter how many student you want to add : ");
        int stdCount = 0;
        stdCount = Integer.parseInt(sc.nextLine().trim().toString());

        for (int i = 1; i <= stdCount; i++) {
            String fn, mn, ln, add, pwd = "";
            int accountType = 0;
            double amount = 0.0;

            System.out.println("Enter customer's first name:");
            fn = sc.nextLine().trim().toLowerCase();

            System.out.println("Enter customer's middle name:");
            mn = sc.nextLine().trim().toLowerCase();

            System.out.println("Enter customer's last name:");
            ln = sc.nextLine().trim().toLowerCase();

            System.out.println("Enter customer's address:");
            add = sc.nextLine().trim();

            System.out.println("Select account type (0 - SAVINGS, 1 - CURRENT, 2 - SALARIED):");
            while (true) {
                try {
                    accountType = Integer.parseInt(sc.nextLine().trim());
                    if (accountType >= 0 && accountType <= 2)
                        break;
                    System.out.println("Please enter a valid account type number (0-2):");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (0-2).");
                }
            }

            System.out.println("Enter initial deposit amount:");
            String temp = sc.nextLine().trim();
            if (!temp.isEmpty()) {// temp.!isempty means enter key is not pressed
                while (true) {
                    try {
                        amount = Double.parseDouble(temp);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a number.");
                    }
                }
            } else {
                System.out.println("you have pressed \"enter\" so your ammount will be 0.0");
            }

            System.out.println(
                    "\nPlease enter a password for your account for future transections\n\t\byou have to remember this for future, so pleasre note it somewhere\b");
            String demoPwd = sc.nextLine();
            while (demoPwd.isEmpty()) {
                System.out.println("please enter the password");
                demoPwd = sc.nextLine();
            }
            pwd = demoPwd.trim();

            Account ac = new Account(fn, mn, ln, add, accountType, amount, pwd);
            accounts.add(ac);
            int accId = ac.getId();
            accIds[accId] = true;

            // updating total balance for bank -> while creating account
            BankingSystem.totalBankBalance += amount;

            if (i != stdCount)
                System.out.println("\n-----------------Enter next person's details-----------------\n");

        }

        System.out.println("\n-----------------List of Account holders-----------------\n");
        showAccounts();

        performOperations();

        // banks total balance at the end
        System.out.println("Bank's Final Balance :-> " + totalBankBalance);

        // closing scanner
        sc.close();
    }

    static public void showAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    static boolean depositBalance(int accId, String password, double amount) {
        for (Account account : accounts) {
            if ((account.getId() == accId) && account.checkPass(password)) {
                // some ops
                if (account.addBal(amount)) {
                    System.out.println("\nCurrent Balance : " + account.getBal());
                    BankingSystem.totalBankBalance += amount;
                    return true;
                }
                // return true;
            }
        }
        return false;
    }

    static boolean withdrawBalance(int accId, String password, double amount) {
        for (Account account : accounts) {
            if ((account.getId() == accId) && account.checkPass(password)) {
                // some ops
                if (account.deductBal(amount)) {
                    BankingSystem.totalBankBalance -= amount;
                    System.out.println("\nCurrent Balance : " + account.getBal());
                    return true;
                }
                // return true;
            }
        }
        return false;
    }

    static boolean viewBalance(int accId, String password) {
        for (Account account : accounts) {
            if ((account.getId() == accId) && account.checkPass(password)) {
                // some ops
                System.out.println("\nCurrent Balance : " + account.getBal());
                return true;
            }
        }
        return false;
    }

    static void showBanksTotalBalance() {
        System.out.println("\nTotal balance of Bank is " + totalBankBalance);
    }

    static void performOperations() {
        while (true) {
            System.out.println("\nChoose one option from below ---> : \n");
            System.out.println("-1  -> to quit the program");
            System.out.println(" 1  -> Deposit the balance");
            System.out.println(" 2  -> Withdraw the amount");
            System.out.println(" 3  -> check your account balance");
            System.out.println(" 4  -> For Balance Transfer");
            int num = Integer.MAX_VALUE;
            num = Integer.parseInt(sc.nextLine().trim());
            if (num == -1)
                break;
            else if(num == 4)
                handle_op_4();
            else if (num > 4 || num < -1)
                System.out.println("Enter valid option");
            else if (num == 3) {
                handle_op_3();
            } else {
                handle_op_1_2(num);
            }
        }
    }

    static void handle_op_3() {
        int uid = -1;
        String upwd = "";

        System.out.println("Enter your account number :");
        uid = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter your Password :");
        upwd = sc.nextLine().trim();

        viewBalance(uid, upwd);
    }

    static void handle_op_1_2(int num) {
        int uid = -1;
        String upwd = "";
        double uamt = -100;

        System.out.println("Enter your account number :");
        uid = Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter your Password :");
        upwd = sc.nextLine().trim();
        System.out.println("Enter amount for transection :");
        uamt = Double.parseDouble(sc.nextLine().trim());

        switch (num) {

            case 1:
                depositBalance(uid, upwd, uamt);
                break;

            case 2:
                withdrawBalance(uid, upwd, uamt);
                break;
            default:
                break;
        }

    }
    
    static void handle_op_4(){
        // int uid = -1;
        // String upwd = "";
        // double uamt = -100;

        int pid1; String pwd1; double amt; int pid2;
        String confirmation = "N";

        
        while (true) {
            System.out.println("Enter your \"Sender's\" account number :");
            pid1 = Integer.parseInt(sc.nextLine().trim());
            // System.out.println("Enter your account number :");            
            // uid = Integer.parseInt(sc.nextLine().trim());
            if(accIds[pid1])
                break;
            else
                System.out.println("Please enter correct Id");
        }

        System.out.println("Enter your \"Sender's\" Password :");
        pwd1 = sc.nextLine().trim();

        System.out.println("Enter amount for transection :");
        amt = Double.parseDouble(sc.nextLine().trim());
        
        while (true) {
            System.out.println("Enter your \"Receiver's\" account number :");
            pid2 = Integer.parseInt(sc.nextLine().trim());
            // System.out.println("Enter your \"Sender's\" account number :");
            // pid1 = Integer.parseInt(sc.nextLine().trim());
            // System.out.println("Enter your account number :");            
            // uid = Integer.parseInt(sc.nextLine().trim());
            if(accIds[pid2])
                break;
            else
                System.out.println("Please enter correct Id");
        }

        System.out.println("Are you sure that you want to proceed with Transfer transection ? Y : N");
        confirmation = sc.nextLine().trim().toUpperCase();

        double bal1 = 0.0, bal2 = 0.0;

        if(confirmation.equals("Y")){
            for(Account ac : accounts){
                if(ac.getId() == pid1 && ac.checkPass(pwd1)){
                    ac.deductBal(amt);
                    bal1 = ac.getBal();
                }else if(ac.getId() == pid2){
                    ac.addBal(amt);
                    bal2 = ac.getBal();
                }
            }
        }
        System.out.println("Transfer procedure successful");
        System.out.println("Sender's remainig balance "+ bal1);
        System.out.println("Receiver's remainig balance "+ bal2);
    }

    }
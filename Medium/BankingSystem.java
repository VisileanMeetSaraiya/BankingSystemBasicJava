package BankingSystemBasicJava.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingSystem {

    static List<Account>accounts = new ArrayList<>() ;
    static double totalBankBalance = 0.0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Account ac1 = new Account("p1", "m1", "l1", "ahem", 0);
        // Account ac2 = new Account("p2", "m2", "l2", "brc", 2, 2340);
        // accounts.add(ac1);
        // accounts.add(ac2);


        System.out.println("Enter how many student you want to add : ");
        int stdCount = 0;
        stdCount = Integer.parseInt(sc.nextLine().toString());

        for(int i = 1;  i <= stdCount; i++){
            String fn,mn,ln,add,pwd = "";
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
                    if (accountType >= 0 && accountType <= 2) break;
                    System.out.println("Please enter a valid account type number (0-2):");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (0-2).");
                }
            }

            System.out.println("Enter initial deposit amount:");
            String temp = sc.nextLine().trim();
            if(!temp.isEmpty()){//temp.!isempty means enter key is not pressed
            while (true) {
                try {
                    amount = Double.parseDouble(temp);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Please enter a number.");
                }
            }
            }else{
                System.out.println("you have pressed \"enter\" so your ammount will be 0.0");
            }

            System.out.println("\nPlease enter a password for your account for future transections\n\t\byou have to remember this for future, so pleasre note it somewhere\b");
            String demoPwd = sc.nextLine();
            while(demoPwd.isEmpty()){
                System.out.println("please enter the password");
                demoPwd = sc.nextLine();
            }
            pwd = demoPwd.trim();

            Account ac = new Account(fn, mn, ln, add, accountType,amount,pwd);
            accounts.add(ac);

            //updating total balance for bank -> while creating account
            BankingSystem.totalBankBalance += amount;

            if( i != stdCount)
            System.out.println("\n-----------------Enter next person's details-----------------\n");

        }

        System.out.println("\n-----------------List of Account holders-----------------\n");
        showAccounts();

        performOperations();

        //closing scanner
        sc.close();
    }

    static public void showAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    static boolean depositBalance(int accId, String password, double amount){
        for (Account account : accounts) {
            if( (account.getId() == accId) && account.checkPass(password)){
                //some ops
                if( account.addBal(amount)){
                    System.out.println("\nCurrent Balance : "+account.getBal());
                    return true;
                }
                // return true;
            }
        }
        return false;
    }

    static void performOperations(){
        while(true){
            System.out.println("\nChoose one option from below ---> : \n");
            System.out.println("-1  -> to quit the program");
            System.out.println("1 -> Deposit the balance");
            System.out.println("2 -> Withdraw the amount");
            System.out.println("3 -> check your account balance");
            int num = Integer.MAX_VALUE;
            num = Integer.parseInt(sc.nextLine().trim());
            if(num == -1)
                break;
            else if(num > 3 || num <-1)
                System.out.println("Enter valid option");
            else{
                int uid = -1;
                String upwd = "";
                double uamt = -100;

                System.out.println("Enter your account number :");
                uid = Integer.parseInt(sc.nextLine().trim());
                System.out.println("Enter your Password :");
                upwd = sc.nextLine().trim();
                System.out.println("Enter amount for transection :");
                uamt =  Double.parseDouble(sc.nextLine().trim());


                switch (num) {
            
                case 1:
                    depositBalance(uid, upwd, uamt);
                    break;
            
                default:
                    break;
            }

        }
    }
    }
}


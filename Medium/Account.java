package BankingSystemBasicJava.Medium;

import java.util.Date;

public class Account {
    static int nextId = 1;

    private int Id = -1;
    private String fName = "";
    private String mName = "";
    private String lName = "";
    private String address = "";
    private AccType accountType = AccType.SAVINGS;
    private double amount = 0.0;
    private Date openinDate ;

    public Account(String fn, String mn, String ln, String add, int at, double amt){

        this.Id = Account.nextId++;

        this.fName = fn;
        this.mName = mn;
        this.lName = ln;
        this.address = add;
        this.amount = amt;
        this.openinDate = new Date();
        try {
            switch (at) {
                case 0:
                    this.accountType = AccType.SAVINGS;
                    break;
                case 1:
                    this.accountType = AccType.CURRENT;
                    break;
                case 2:
                    this.accountType = AccType.SALARIED;
                    break;
            
                default:
                    this.accountType = AccType.SAVINGS;
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error while asigning account type , number is not in range");
        }
    }
    public Account(String fn, String mn, String ln, String add, int at){

        this.Id = Account.nextId++;

        this.fName = fn;
        this.mName = mn;
        this.lName = ln;
        this.address = add;
        this.openinDate = new Date();
        try {
            switch (at) {
                case 0:
                    this.accountType = AccType.SAVINGS;
                    break;
                case 1:
                    this.accountType = AccType.CURRENT;
                    break;
                case 2:
                    this.accountType = AccType.SALARIED;
                    break;
            
                default:
                    this.accountType = AccType.SAVINGS;
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error while asigning account type , number is not in range");
        }
    }
    
    @Override
    public String toString() {
    return "Account Details:\n" +
           "ID: " + Id + "\n" +
           "First Name: " + fName + "\n" +
           "Middle Name: " + mName + "\n" +
           "Last Name: " + lName + "\n" +
           "Address: " + address + "\n" +
           "Account Type: " + accountType + "\n" +
           "Amount: " + amount + "\n" +
           "Opening Date: " + (openinDate != null ? openinDate.toString() : "Not Set")+ "\n\n";
    }

}
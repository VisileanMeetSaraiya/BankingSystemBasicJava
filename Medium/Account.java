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
    private Date openinDate;
    private String password = "";

    public Account(String fn, String mn, String ln, String add, int at, double amt, String pwd) {

        this.Id = Account.nextId++;
        this.password = pwd;

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

        System.out.println("\n\nYour account no. is ---> " + this.Id + " and password is ---> " + this.password + "\n");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Timer related exception");
        }

        // try {
        // System.out.println("Your account no. is"+this.Id+" and password is
        // "+this.password);
        // wait(1000);
        // } catch (Exception e) {
        // 
        // System.out.println("Timer related exception");
        // }

    }

    public Account(String fn, String mn, String ln, String add, int at, String pwd) {

        this.Id = Account.nextId++;
        this.password = pwd;

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

        System.out.println("\n\nYour account no. is ---> " + this.Id + " and password is ---> " + this.password + "\n");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // 
            System.out.println("Timer related exception");
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
                "Opening Date: " + (openinDate != null ? openinDate.toString() : "Not Set") + "\n\n";
    }

    public int getId() {
        return this.Id;
    }

    public double getBal() {
        return this.amount;
    }

    public boolean checkPass(String passwordToCheck) {
        return passwordToCheck.equals(this.password);
    }

    public boolean addBal(double bal) {
        if (bal <= 0) {
            System.out.println("Enter valid balance");
            return false;
        }
        this.amount += bal;
        return true;
    }

    public boolean deductBal(double bal) {
        if (bal <= 0) {
            System.out.println("Enter valid balance");
            return false;
        }
        this.amount -= bal;
        return true;
    }

}
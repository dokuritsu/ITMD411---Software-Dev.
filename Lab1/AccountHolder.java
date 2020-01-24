package Lab1;

public class AccountHolder {
    private double balance;
    private static double annualInterestRate;

    //Constructor
    public AccountHolder(double balance){
        //Check if the balance is less than 0
        //If so, set it to 0 automatically, otherwise set it to requested balance
        this.balance = balance < 0 ? 0:balance;
    }

    //Method to deposit to account
    public void deposit(double deposit) {
        this.balance += this.balance + deposit;
    }

    //Method to withdraw from account
    public void withdrawal(double withdrawal) {
        //First check if the withdrawal would put balance less than 50
        double check = this.balance - withdrawal;
        if(check < 50){
            System.out.println("Balance must hold to at least $50");
        } else {
            this.balance = check;
        }
    }

    //Method to calculate monthly interest
    public void monthlyInterest() {
        this.balance += this.balance * (annualInterestRate/12.0);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public static void setAnnualInterestRate(double annualInterestRate) {
        AccountHolder.annualInterestRate = annualInterestRate;
    }
}

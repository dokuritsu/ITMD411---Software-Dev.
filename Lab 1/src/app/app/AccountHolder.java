/* Lab 1 - Bank Account Simulator
   By: Laura Pereda
*/
package app;

public class AccountHolder {
    private double balance;
    private static double annualInterestRate = 0.04;

    //Constructor
    public AccountHolder(double balance){
        //Check if the balance is less than 0
        //If so, set it to 0 automatically, otherwise set it to requested balance
        if(this.balance < 0){
            throw new IllegalArgumentException("Initial Balance must be non-negative");
        } else {
            this.balance = balance;
        }
    }

    //Method to deposit to account
    public void deposit(double deposit) {
        if(deposit < 0){
            throw new IllegalArgumentException("This is for depositing, not withdrawing");
        } else {
            this.balance += deposit;
        }
    }

    //Method to withdraw from account
    public void withdrawal(double withdrawal) {
        //First check if the withdrawal would put balance less than 50
        if((this.balance - withdrawal) < 50){
            System.out.println("Balance must hold to at least $50. Unable to perform transaction.");
        } else {
            this.balance -= withdrawal;
        }
    }

    //Method to calculate monthly interest
    public void monthlyInterest() {
        this.balance += (this.balance * (annualInterestRate/12.0));
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

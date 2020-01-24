package Lab1;

import java.util.Scanner;

public class AccountHolderTest
{
    public static void main(String[] args) {

        //Initially the interest for the bank will be set to 4%
        double interestRate = 0.04;

        //Ask the user to for the initial account balance
        System.out.println("Please provide the initial account balance. Note: The annual interest rate is 4%.");
        Scanner scan = new Scanner(System.in);
        double initialBal = scan.nextDouble();

        //Create object with provided initial balance
        AccountHolder ah1 = new AccountHolder(initialBal);

        //Ask the user to make a deposit
        System.out.println("Please provide an amount to deposite into account: ");

        //Do the deposit
        ah1.deposit(scan.nextDouble());

        //Ask the user to make a withdrawal
        System.out.println("Please provide an amount to withdraw from account:");
        
        //Withdraw
        ah1.withdrawal(scan.nextDouble());

        //Display the ending balance
    }
}
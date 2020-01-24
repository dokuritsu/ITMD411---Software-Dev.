/* Lab 1 - Bank Account Simulator
   By: Laura Pereda
*/

package Lab1;

import java.util.Scanner;

public class AccountHolderTest
{
    public static void main(final String[] args) {


        // Initially the interest for the bank will be set to 4%
        // Ask the user to for the initial account balance
        System.out.println("Please provide the initial account balance. Note: The annual interest rate is 4%.");
        Scanner scan = new Scanner(System.in);
        double initialBal = scan.nextDouble();

        // Create object with provided initial balance
        AccountHolder ah1 = new AccountHolder(initialBal);

        // Ask the user to make a deposit
        System.out.println("Please provide an amount to deposit into account: ");

        // Do the deposit
        ah1.deposit(scan.nextDouble());

        // Ask the user to make a withdrawal
        System.out.println("Please provide an amount to withdraw from account:");

        // Withdraw
        ah1.withdrawal(scan.nextDouble());

        // Calculate the balance at the end of month
        ah1.monthlyInterest();

        // Inform the user of the final balance

        
        String finalBal = String.format("Ending balance for the month including monthly interest: %.2f",
                ah1.getBalance());
        System.out.println(finalBal);

        // Close the scanner
        scan.close();

    }
}
package lab3;

import java.util.Collections;

public class Records extends BankRecords {

    public Records () {
        super();
    }

    /*
    *   Constructor with parameters
    */
    public Records(String id, int age, String sex, String region, double income, String martialStatus, int totalChildren, String hasCar, String save_act, String current_act, String mortgage, String pep) {
        super(id, age, sex, region, income, martialStatus, totalChildren, hasCar, save_act, current_act, mortgage, pep);
    }
    
    public static void main(String[] args) {
        Records records = new Records();
        records.readData();


    }

    /*
    *   Method to determine average income by sec
    */
    public static void AvgIncome(Records aRecords){
        Collections.sort(aRecords, new SexComparator());
    }
}
package lab3;

import java.util.ArrayList;
import java.util.Collections;

public class Records extends BankRecords {

    public Records() {
        super();
    }

    /*
     * Constructor with parameters
     */
    public Records(String id, int age, String sex, String region, double income, String martialStatus,
            int totalChildren, String hasCar, String save_act, String current_act, String mortgage, String pep) {
        super(id, age, sex, region, income, martialStatus, totalChildren, hasCar, save_act, current_act, mortgage, pep);
    }

    public static void main(String[] args) {
        Records records = new Records();
        records.readData();

        //See the first 10 unsorted items
        System.out.println("Unsorted");
        for(int i = 0; i < records.getbArrayList().size(); i++){
            System.out.println(records.getbArrayList().get(i));
        }

        //See the first 10 sorted items
        AvgIncome(records.getbArrayList());
        for(int i = 0; i < records.getbArrayList().size(); i++){
            System.out.println(records.getbArrayList().get(i));
        }

    }

    /*
     * Access bArrayList in BankRecords
     */
    @Override
    public ArrayList<BankRecords> getbArrayList() {
        return super.getbArrayList();
    }

    /*
     * Method to determine average income by sec
     */
    public static void AvgIncome(ArrayList<BankRecords> arrayList){
        Collections.sort(arrayList, new SexComparator());
    }
}
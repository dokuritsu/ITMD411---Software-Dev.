package lab3;

import java.text.DecimalFormat;
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

        // Calculate the average income for sex
        System.out.println("Data Analytic Results: ");
        AvgIncome(records.getbArrayList());
        locationComp(records.getbArrayList());
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
     * Also counts females with mortgage & sav. acct
     */
    public static void AvgIncome(ArrayList<BankRecords> arrayList){

        // Sort the data by males vs females...
        Collections.sort(arrayList, new SexComparator());

        /*
        *   To keep track of:
        *   Counts for # of males & females
        *   Total income from males & females
        */
        int mCount, fCount, mort_sav;
        double totalMIncome, totalFIncome;
        mCount = fCount = mort_sav = 0;
        totalFIncome = totalMIncome = 0;

        for (int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i).getSex().equals("FEMALE")){
                // Increment fCount
                fCount++;

                // Add income to totalFIncome
                totalFIncome += arrayList.get(i).getIncome();

                if(arrayList.get(i).getMortgage().equals("YES") && arrayList.get(i).getSave_act().equals("YES")){
                    mort_sav++;
                }
            } else {
                // Increment mCount
                mCount++;

                // Add income to totalMIncome
                totalMIncome += arrayList.get(i).getIncome();
            }
        }

        double avgFIncome = totalFIncome/fCount;
        double avgMIncome = totalMIncome/mCount;
        
        DecimalFormat df = new DecimalFormat("##.##");
        System.out.println("Average income for Females: $" + df.format(avgFIncome));
        System.out.println("Average income for Males: $" + df.format(avgMIncome));

        System.out.println("Num. of Females with Mortgage & Savings Acct: " + mort_sav);
    }

    public static void locationComp(ArrayList<BankRecords> arrayList) {

        // Sort the records based on Location
        Collections.sort(arrayList, new LocationComparator());

        /*
        *   Keep track of children per region
        */
        int innerChildren, ruralChildren, suburbanChildren, townChildren;
        innerChildren = ruralChildren = suburbanChildren = townChildren = 0;

        for (int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i).getHasCar().equals("YES") && arrayList.get(i).getTotalChildren() == 1 && arrayList.get(i).getSex().equals("MALE")) {
                if (arrayList.get(i).getRegion().equals("INNER_CITY")) {
                    innerChildren++;
                } else if (arrayList.get(i).getRegion().equals("RURAL")) {
                    ruralChildren++;
                } else if (arrayList.get(i).getRegion().equals("SUBURBAN")) {
                    suburbanChildren++;
                } else {
                    townChildren++;
                }
            }
        }

        System.out.println("Innercity region males with car & 1 child: " + innerChildren);
        System.out.println("Rural region males with car & 1 child: " + ruralChildren);
        System.out.println("Suburban region males with car & 1 child: " + suburbanChildren);
        System.out.println("Town region males with car & 1 child: " + townChildren);
    }
}
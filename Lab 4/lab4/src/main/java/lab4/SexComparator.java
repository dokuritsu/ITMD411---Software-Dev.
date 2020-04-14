package lab4;

import java.util.Comparator;

public class SexComparator implements Comparator<BankRecords> {

    @Override
    public int compare(BankRecords o1, BankRecords o2) {
        int result = o1.getSex().compareTo(o2.getSex());
        return result;
    }
    
}
package lab3;

import java.util.Comparator;

public class LocationComparator implements Comparator<BankRecords> {

    @Override
    public int compare(BankRecords o1, BankRecords o2) {
        int result = o1.getRegion().compareTo(o2.getRegion());
        return result;
    }
    
}
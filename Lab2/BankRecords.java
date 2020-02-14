import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BankRecords extends Client{
    private String id;
    private int age;
    private String sex;
    private String region;
    private int income;
    private String martialStatus;
    private int totalChildren;
    private String hasCar;
    private String save_act;
    private String current_act;
    private String mortgage;
    private String pep;

    //ArrayList to manage data
    private static ArrayList<BankRecords> bArrayList;

    /*
    *   Constructor with no parameters
    */
    public BankRecords(){

    }

    /*
    *   Constructor with parameters
    */
    public BankRecords(String id, int age, String sex, String region, int income, String martialStatus,
            int totalChildren, String hasCar, String save_act, String current_act, String mortgage, String pep) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.region = region;
        this.income = income;
        this.martialStatus = martialStatus;
        this.totalChildren = totalChildren;
        this.hasCar = hasCar;
        this.save_act = save_act;
        this.current_act = current_act;
        this.mortgage = mortgage;
        this.pep = pep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getMartialStatus() {
        return martialStatus;
    }

    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public int getTotalChildren() {
        return totalChildren;
    }

    public void setTotalChildren(int totalChildren) {
        this.totalChildren = totalChildren;
    }

    public String getHasCar() {
        return hasCar;
    }

    public void setHasCar(String hasCar) {
        this.hasCar = hasCar;
    }

    public String getSave_act() {
        return save_act;
    }

    public void setSave_act(String save_act) {
        this.save_act = save_act;
    }

    public String getCurrent_act() {
        return current_act;
    }

    public void setCurrent_act(String current_act) {
        this.current_act = current_act;
    }

    public String getMortgage() {
        return mortgage;
    }

    public void setMortgage(String mortgage) {
        this.mortgage = mortgage;
    }

    public String getPep() {
        return pep;
    }

    public void setPep(String pep) {
        this.pep = pep;
    }

    /*
    *   This methods will read from the CSV file and place data into an ArrayList
    */
    @Override
    public void readData() {

        BufferedReader br = null;
        String line = "";
        bArrayList = new ArrayList<>();

        try{
            //Create and initialize a new reader object to read from the CSV file
            FileReader fr = new FileReader("bank-Detail.csv");
            br = new BufferedReader(fr);

            while((line = br.readLine()) != null){
                String[] data = line.split(",");

                //Create an empty BankRecord object & set the data
                BankRecords bankRecords = new BankRecords();
                bankRecords.setId(data[0]);                                         //data[0] : String id
                bankRecords.setAge(Integer.parseInt(data[1]));                      //data[1] : int age
                bankRecords.setSex(data[2]);                                        //data[2] : String sex
                bankRecords.setRegion(data[3]);                                     //data[3] : String region
                bankRecords.setIncome(Integer.parseInt(data[4]));                   //data[4] : int income
                bankRecords.setMartialStatus(data[5]);                              //data[5] : String region
                bankRecords.setTotalChildren(Integer.parseInt(data[6]));             //data[6] : int children
                bankRecords.setHasCar(data[7]);                                     //data[7] : String car
                bankRecords.setSave_act(data[8]);                                   //data[8] : String sav_act
                bankRecords.setCurrent_act(data[9]);                                //data[9] : String curr_act
                bankRecords.setMortgage(data[10]);                                  //data[10]: String mortgage
                bankRecords.setPep(data[11]);                                       //data[11]: String pep

                //Add object to empty bArrayList
                bArrayList.add(bankRecords);

                //To check
                System.out.println(bankRecords.toString());
            }

            //Call printData()
            processData();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (br != null){
                try{
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    *   This methods will process data in ArrayList and add all data into each of the instance fields via setters.
    *   An array of objects will be used to store data for each instance field
    */
    @Override
    public void processData() {
        // TODO Auto-generated method stub
        printData();

    }

    /*
    *   This methods will print the first 25 records for various fields via getters.
    *   The following records will be printed: ID, AGE, SEX, REGION, INCOME, and MORTGAGE
    */
    @Override
    public void printData() {

    }

    @Override
    public String toString() {
        return "BankRecords [age=" + age + ", bArrayList=" + bArrayList + ", current_act=" + current_act + ", hasCar="
                + hasCar + ", id=" + id + ", income=" + income + ", martialStatus=" + martialStatus + ", mortgage="
                + mortgage + ", pep=" + pep + ", region=" + region + ", save_act=" + save_act + ", sex=" + sex
                + ", totalChildren=" + totalChildren + "]";
    }

    public static void main(String[] args) {
        //Call readData to begin reading and parsing data, which will lead to printing
        
    }

}
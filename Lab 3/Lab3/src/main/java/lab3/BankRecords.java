package lab3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class BankRecords extends Client {

    //BankRecords variables
    private String id;
    private int age;
    private String sex;
    private String region;
    private double income;
    private String martialStatus;
    private int totalChildren;
    private String hasCar;
    private String save_act;
    private String current_act;
    private String mortgage;
    private String pep;
    private static ArrayList<BankRecords> bArrayList;

    /*
    *   Constructor with no parameters
    */
    protected BankRecords() {
    }

    /*
    *   Constructor with parameters
    */
    public BankRecords(String id, int age, String sex, String region, double income, String martialStatus,
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

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        BankRecords br = new BankRecords();
        br.readData();
        br.printData();
    }

    @Override
    public void readData() {
        BufferedReader br = null;
        String line = "";
        bArrayList = new ArrayList<>();

        try{
            //Create and initialize a new reader object to read from the CSV file
            String workingDir = System.getProperty("user.dir");
            System.out.println(workingDir);
            FileReader fr = new FileReader(workingDir+"\\bank-Detail.csv");
            br = new BufferedReader(fr);

            while((line = br.readLine()) != null){
                String[] data = line.split(",");

                //Create an empty BankRecord object & set the data
                BankRecords bankRecords = new BankRecords();
                bankRecords.setId(data[0]);                                         //data[0] : String id
                bankRecords.setAge(Integer.parseInt(data[1]));                      //data[1] : int age
                bankRecords.setSex(data[2]);                                        //data[2] : String sex
                bankRecords.setRegion(data[3]);                                     //data[3] : String region
                bankRecords.setIncome(Double.parseDouble(data[4]));                   //data[4] : int income
                bankRecords.setMartialStatus(data[5]);                              //data[5] : String region
                bankRecords.setTotalChildren(Integer.parseInt(data[6]));             //data[6] : int children
                bankRecords.setHasCar(data[7]);                                     //data[7] : String car
                bankRecords.setSave_act(data[8]);                                   //data[8] : String sav_act
                bankRecords.setCurrent_act(data[9]);                                //data[9] : String curr_act
                bankRecords.setMortgage(data[10]);                                  //data[10]: String mortgage
                bankRecords.setPep(data[11]);                                       //data[11]: String pep

                //Add object to empty bArrayList
                bArrayList.add(bankRecords);
            }

            //Call printData()
            // processData();

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

    @Override
    public void printData() {
        System.out.println("Programmer: Laura Pereda");
        //Header to print information
        System.out.println("ID\t\tAGE\t\tSEX\t\tREGION\t\tINCOME\t\t\tMORTGAGE");

        //Created a for loop to only print out the first 25 objects with information
        for(int i = 0; i <= 25; i++){
            String id = bArrayList.get(i).getId();
            String removeID = id.replace("id", "");
            int age = bArrayList.get(i).getAge();
            String sex = bArrayList.get(i).getSex();
            String region = bArrayList.get(i).getRegion();
            Double income = bArrayList.get(i).getIncome();
            String mortgage = bArrayList.get(i).getMortgage();

            /*  Because of formatting issues, I decided to abbreiviate REGION
            *   INNER_CITY = IC
            *   SUBURBAN = SUB
            *   Note: I removed the string "id" from the ID of each object since it is redundant to maintain
            */
            String abbriviatedRegion = "";
            String printOut = "";
            if(region.equals("INNER_CITY")){
                abbriviatedRegion = "IC";
                printOut = String.format("%s\t\t%d\t\t%s\t\t%s\t\t%.3f\t\t%s", removeID, age, sex, abbriviatedRegion, income, mortgage);
            } else if(region.equals("SUBURBAN")) {
                abbriviatedRegion = "SUB";
                printOut = String.format("%s\t\t%d\t\t%s\t\t%s\t\t%.3f\t\t%s", removeID, age, sex, abbriviatedRegion, income, mortgage);

            } else {
                printOut = String.format("%s\t\t%d\t\t%s\t\t%s\t\t%.3f\t\t%s", removeID, age, sex, region, income, mortgage);
            }
            System.out.println(printOut);
            //System.out.println(id + "\t\t" + age + "\t\t" + sex + "\t\t" + region + "\t\t" + income + "\t\t" + mortgage);
        }

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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
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

    public ArrayList<BankRecords> getbArrayList() {
        return bArrayList;
    }

    public static void setbArrayList(ArrayList<BankRecords> bArrayList) {
        BankRecords.bArrayList = bArrayList;
    }

    
    
}

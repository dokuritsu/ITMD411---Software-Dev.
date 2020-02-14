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
        // TODO Auto-generated method stub

    }

    /*
    *   This methods will process data in ArrayList and add all data into each of the instance fields via setters.
    *   An array of objects will be used to store data for each instance field
    */
    @Override
    public void processData() {
        // TODO Auto-generated method stub

    }

    /*
    *   This methods will print the first 25 records for various fields via getters.
    *   The following records will be printed: ID, AGE, SEX, REGION, INCOME, and MORTGAGE
    */
    @Override
    public void printData() {
        // TODO Auto-generated method stub

    }
    
}
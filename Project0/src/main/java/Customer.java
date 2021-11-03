public class Customer extends User{
    private int id;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private double balance;
    private String accountType;
    private int accountID;
    private boolean isCust;
    private boolean accountStatus;

    public Customer(){

    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Customer(int id, String fname, String lname){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public Customer(int id, String fname, String lname, boolean isCust){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.isCust = isCust;
    }



    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public boolean isCust() {
        return isCust;
    }

    public void setCust(boolean cust) {
        isCust = cust;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '}';
    }


}

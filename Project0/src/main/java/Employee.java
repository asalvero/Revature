public class Employee extends User{
    private int id;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private boolean isCust;

    public Employee(){

    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(int id, String fname, String lname){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public Employee(int id, String fname, String lname, boolean isCust){
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

    @Override
    public String toString() {
        return "Employee{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}

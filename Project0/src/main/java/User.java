public class User {
    private int id;
    private String fname;
    private String lname;
    private String username;
    private String password;
    private boolean isCust;

    public User(){

    }

    public User(int id, String fname, String lname, boolean isCust) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.isCust = isCust;
    }

    public User(String fname, String lname, boolean isCust) {
        this.fname = fname;
        this.lname = lname;
        this.isCust = isCust;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsCust() {
        return isCust;
    }

    public void setIsCust(boolean isCust) {
        this.isCust = isCust;
    }

    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{

    Connection connection;

    public CustomerDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Customer signIn(Customer customer) throws SQLException { // check if is customer here?
        String sql = "select * from user where username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            int id = resultSet.getInt("userID");
            String fname = resultSet.getString("firstname");
            String lname = resultSet.getString("lastname");
            boolean isCust = resultSet.getBoolean("isCust");
            customer = new Customer(id, fname, lname, isCust);
            if(isCust == true){
                String sql1 = "select * from account where userID = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setInt(1, customer.getId());
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                if(resultSet1.next()) {
                    double balance = resultSet1.getDouble("balance");
                    String accountType = resultSet1.getString("accountType");
                    int accountID = resultSet1.getInt("accountID");
                    boolean status = resultSet1.getBoolean("status");
                    customer.setBalance(balance);
                    customer.setAccountType(accountType);
                    customer.setAccountID(accountID);
                    customer.setAccountStatus(status);
                }else {
                    System.out.println();
                }

                System.out.println("Login successful! Welcome "+ fname + " "+ lname +"!");
            }else {
                System.out.println("You are not a customer. Please sign up to join!");
            }
        }else {
            System.out.println("Oops! There was an error. Please try again.");
        }
        return customer;
    }

    @Override
    public void apply(Customer customer) throws SQLException {
        String sql = "insert into account (balance, accountType, userID) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, customer.getBalance());
        preparedStatement.setString(2, customer.getAccountType());
        preparedStatement.setInt(3, customer.getId());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Thank you for applying!");
        else
            System.out.println("Oops! Something went wrong. Please try again.");
    }

    @Override
    public List<Customer> viewBalance(Customer customer) throws SQLException {
        List<Customer> accounts = new ArrayList<>();
        String sql1 = "select * from account where userID = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setInt(1, customer.getId());
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        while(resultSet1.next()){
            int id = resultSet1.getInt("accountID");
            String accountType = resultSet1.getString("accountType");
            double balance = resultSet1.getDouble("balance");
            System.out.println("Here is your account info: Account ID: " +id+", Account Type: "+ accountType +", Balance: "+ balance);
        }
        return accounts;
    }

    @Override
    public void withdraw(double amount, int id, Customer customer) throws SQLException {
        double tempBalance = customer.getBalance() - amount;
        if(tempBalance >= 0 ){
            if(customer.isAccountStatus() == true) {
                String sql = "update account set balance = balance -  ? where accountID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, id);

                int count = preparedStatement.executeUpdate();
                if (count > 0) {
                    customer.setBalance(customer.getBalance() - amount);
                    String sql1 = "insert into transactions (transType, amount, accountID) values(?,?,?)";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                    preparedStatement1.setString(1, "withdraw");
                    preparedStatement1.setDouble(2, amount);
                    preparedStatement1.setInt(3, id);
                    int num = preparedStatement1.executeUpdate();
                    if (num > 0)
                        System.out.println("Withdraw complete! Your balance is: " + customer.getBalance());
                    else
                        System.out.println("Oops! Something went wrong. Please try again.");
                }
            }else{
                System.out.println("Account ");
            }
        }else {
            System.out.println("Not enough in account. Please try again later.");
        }


    }

    @Override
    public void deposit(double amount, int id, Customer customer) throws SQLException {
        String sql2 = "select * from account where accountID = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        preparedStatement2.setInt(1, id);
        ResultSet resultSet1 = preparedStatement2.executeQuery();
        if(resultSet1.next()) {
            double balance = resultSet1.getDouble("balance");
            String accountType = resultSet1.getString("accountType");
            int accountID = resultSet1.getInt("accountID");
            boolean status = resultSet1.getBoolean("status");
            customer.setBalance(balance);
            customer.setAccountType(accountType);
            customer.setAccountID(accountID);
            customer.setAccountStatus(status);
            if(customer.isAccountStatus() == true){
                String sql = "update account set balance = balance + ? where accountID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, id);
                int count = preparedStatement.executeUpdate();
                if(count > 0) {
                    customer.setBalance(customer.getBalance() + amount);
                    String sql1 = "insert into transactions (transType, amount, accountID) values(?,?,?)";;
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                    preparedStatement1.setString(1, "deposit");
                    preparedStatement1.setDouble(2, amount);
                    preparedStatement1.setInt(3, id);
                    int num = preparedStatement1.executeUpdate();
                    if (num > 0)
                        System.out.println("Deposit complete!");
                    else
                        System.out.println("Oops! Something went wrong. Please try again.");
                }else {
                    System.out.println("Oops! There was an error. Please try again.");
                }
            } else{
                System.out.println("Account has not been approved yet. Please wait for account to be approved to use.");
            }
        }
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    Connection connection;

    public EmployeeDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Employee signIn(Employee employee) throws SQLException {
        String sql = "select * from user where username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getUsername());
        preparedStatement.setString(2, employee.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            int id = resultSet.getInt("userID");
            String fname = resultSet.getString("firstname");
            String lname = resultSet.getString("lastname");
            boolean isCust = resultSet.getBoolean("isCust");
            employee = new Employee (fname, lname);
            if(isCust == false){
                System.out.println("Login successful! Welcome "+ fname + " "+ lname +"!");
            }else {
                System.out.println("You are not a customer. Please sign up to join!");
            }
        }else {
            System.out.println("Oops! There was error. Please try again.");
        }
        return employee;
    }

    @Override
    public List<Customer> showCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from user where isCust = true";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            int id = resultSet.getInt("userID");
            String fname = resultSet.getString("firstname");
            String lname = resultSet.getString("lastname");
            Customer customer = new Customer(id, fname, lname);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public List<Customer> viewBalanceOfCustomer(Customer customer, int id) throws SQLException {
        List<Customer> accounts = new ArrayList<>();
        String sql1 = "{CALL sp_GetCustAccount(?)}";
        CallableStatement callableStatement = connection.prepareCall(sql1);
        callableStatement.setInt(1, id);
        ResultSet resultSet1 = callableStatement.executeQuery();
        while(resultSet1.next()){
            String fname = resultSet1.getString("firstname");
            String lname = resultSet1.getString("lastname");
            int accountId = resultSet1.getInt("accountID");
            boolean status = resultSet1.getBoolean("status");
            String accountType = resultSet1.getString("accountType");
            double balance = resultSet1.getDouble("balance");
            System.out.println("This is customer " +fname+" "+lname+"'s Account ID: "+accountId+", Account Status: "+status+", Account Type: "+accountType+", Balance: "+balance);
        }
        return accounts;
    }

    @Override
    public void approveAccount(int id) throws SQLException {
        String sql = "update account set status = true where userID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Account has been approved.");
        else
            System.out.println("Oops! There was an error. Please try again.");
    }

    @Override
    public void denyAccount(int id) throws SQLException {
        String sql = "update account set status = false where userID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Account has been denied.");
        else
            System.out.println("Oops! There was an error. Please try again.");
    }

}

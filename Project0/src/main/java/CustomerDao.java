import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    Customer signIn(Customer customer) throws SQLException;
    void apply(Customer customer) throws SQLException;
    List<Customer> viewBalance(Customer customer) throws SQLException;
    void withdraw(double amount, int id, Customer customer) throws SQLException;
    void deposit(double amount, int id, Customer customer) throws SQLException;
}
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    Employee signIn(Employee employee) throws SQLException;
    List<Customer> showCustomers() throws SQLException;
    List<Customer> viewBalanceOfCustomer(Customer customer, int id) throws SQLException;
    void approveAccount(int id) throws SQLException;
    void denyAccount(int id) throws SQLException;
}

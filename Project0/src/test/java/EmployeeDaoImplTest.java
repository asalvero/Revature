import junit.framework.TestCase;

import java.sql.SQLException;

public class EmployeeDaoImplTest extends TestCase {

    public void testSignIn() throws SQLException {
        // Arrange
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        String username = "ajalvero";
        String password = "tmblrbyz";
        Employee employee1 = new Employee(username, password);


        //Act
        Employee employee = employeeDao.signIn(employee1);

        //Assert
        Employee employee2 = new Employee("AJ", "Alvero");

        assertEquals(employee.getFname(), employee2.getLname());

    }

    public void testApproveAccount() {


    }
}
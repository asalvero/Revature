import junit.framework.TestCase;

import java.sql.SQLException;

public class CustomerDaoImplTest extends TestCase {

    public void testSignIn() throws SQLException {
        //Arrange
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        String username = "mschmitz";
        String password = "brownsrock";
        Customer customer1 = new Customer(username, password);

        //Act
        Customer customer = customerDao.signIn(customer1);

        //Assert
        Customer customer2 = new Customer(2,"Matt", "Schmitz");

        assertEquals(customer.getId(),customer2.getId());
        assertEquals(customer.getFname(), customer2.getFname());
        assertEquals(customer.getLname(), customer2.getLname());
    }
}
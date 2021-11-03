import java.sql.*;

public class UserDaoImpl implements UserDao {

    Connection connection;

    public UserDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void signUp(User user) throws SQLException {
        String sql = "insert into user (firstname, lastname, username, password, isCust) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFname());;
        preparedStatement.setString(2, user.getLname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setBoolean(5, true);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Thank you for signing up!");
        else
            System.out.println("Oops! There was an error. Please try again.");
    }
}

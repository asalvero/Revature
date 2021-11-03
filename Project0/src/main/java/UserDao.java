import java.sql.SQLException;

public interface UserDao {
    void signUp(User user) throws SQLException;
}

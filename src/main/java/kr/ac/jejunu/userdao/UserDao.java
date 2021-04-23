package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final JdbcContext jdbcContext;
    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        return jdbcContext.jdbcContextForGet(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }

    public void insert(User user) throws SQLException {
        jdbcContext.jdbcContextForInsert(user, connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name,password) value (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        });
    }

    public void update(User user) throws SQLException {
        jdbcContext.jdbcContextForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name=?,password=? where id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            return preparedStatement;
        });
    }

    public void delete(Integer id) throws SQLException {
        jdbcContext.jdbcContextForUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from userinfo where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        });
    }

}

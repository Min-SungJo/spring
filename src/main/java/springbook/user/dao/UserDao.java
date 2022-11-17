package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "spring", "book"
        );

        PreparedStatement ps = conn.prepareStatement(
                "insert into user(id, nmae, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
    }
}

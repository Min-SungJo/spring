package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public abstract class UserDao {

    /**중복되는 코드인 Connection을 독립적인 메서드로 만들어서 중복을 제거했다.
     * db에 접속하는 연결정보를 담고 있는 메서드
     * **/
//    private Connection getConnection() throws ClassNotFoundException, SQLException{
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://localhost/springbook", "spring", "book"
//        );
//        return conn;
//    }
    /**메소드의 구현은 서브 클래스가 담당하는 추상메서드, db에 접속하는 Connection을 담았다.
     *다양한 db에 접근할 수 있게 리팩토링한 것 **/
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
    /**N사 DB connection 생성코드**/
    public class NUserDao extends UserDao{
        public Connection getConnection() throws  ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/springbook", "spring", "book");
            return conn;
        }
    }
    /**D사 DB connection 생성코드**/
    public class DUserDao extends UserDao{
        public Connection getConnection() throws  ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/springbook", "spring", "book");
            return conn;
        }
    }
    public void add(User user) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "insert into user(id, nmae, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://locallhost/springbook", "spring", "book"
        );
        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id =?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

}

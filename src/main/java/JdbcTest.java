import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/school"
                + "?useSSL=false&serverTimezone=Asia/Shanghai";
        String username = System.getenv("DB_USERNAME"
        );
        String password = System.getenv("DB_PASSWORD");

        try (Connection conn =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("数据库连接成功！");
            String sql="SELECT id,name,age FROM student";
            try (PreparedStatement ps= conn.prepareStatement(sql);
                 ResultSet rs=ps.executeQuery()){
                while (rs.next()) {
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    int age=rs.getInt("age");

                    System.out.println(id+" "+name+" "+age);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }






    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String url="jdbc:mysql://localhost:3306/school"+"?useSSL=false&serverTimezone=Asia/Shanghai";
        String username = System.getenv("DB_USERNAME"
        );
        String password = System.getenv("DB_PASSWORD");

        try(Connection conn= DriverManager.getConnection(url,username,password)){
            StudentDao dao=new StudentDao();
            Student student=dao.findById(conn,1003);
            int rows=dao.deleteStudent(conn,0);
            System.out.println("受影响的行数:"+rows);
            List<Student> student1=dao.findAll(conn);
            for (int i = 0; i < student1.size(); i++) {
                System.out.println(student1.get(i));
            }
            if(student !=null){
                System.out.println(student);
            }else {
                System.out.println("没有找到该学生");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

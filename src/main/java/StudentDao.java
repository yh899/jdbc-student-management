import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public Student findById(Connection conn,int id)throws SQLException{
        String sql="SELECT id,name,age,class_name FROM student WHERE id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,id);
            try (ResultSet rs= ps.executeQuery()){
                if(rs.next()){
                    Student student=new Student(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getString("class_name"));
                    return student;
                }
            }
        }
        return null;



    }
    public List<Student> findAll(Connection conn) throws SQLException{
        List<Student> students=new ArrayList<>();
        String sql="SELECT id,name,age,class_name FROM student";
        try(PreparedStatement ps= conn.prepareStatement(sql)){
            try (ResultSet rs= ps.executeQuery()){
                while (rs.next()){
                    Student student=new Student(rs.getInt("id"),rs.getString("name"),rs.getInt("age"), rs.getString("class_name"));
                    students.add(student);

                }
            }
            return students;
        }

    }
    public int addStudent(Connection conn,Student student)throws SQLException{
        String sql="INSERT INTO student(id,name,age,class_name) VALUES(?,?,?,?)";
        try(PreparedStatement ps= conn.prepareStatement(sql)) {
            ps.setInt(1,student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3,student.getAge());
            ps.setString(4,student.getClass_name());
            int rows=ps.executeUpdate();
            return rows;
        }
    }
    public int updateStudent(Connection conn,Student student)throws SQLException{
        String sql="UPDATE student SET name=?,age=? WHERE Id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,student.getName());
            ps.setInt(2,student.getAge());
            ps.setInt(3,student.getId());
            int rows=ps.executeUpdate();
            return rows;
        }
    }
    public int deleteStudent(Connection conn, int id)throws Exception{
        String sql="DELETE FROM student WHERE Id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            return rows;
        }
    }


}

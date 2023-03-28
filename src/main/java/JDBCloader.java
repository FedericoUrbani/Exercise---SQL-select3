import java.sql.*;
import java.util.ArrayList;

public class JDBCloader {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost:3306/newdb";
        String user = "developer";
        String password = "developer1";
        ArrayList students = new ArrayList();


        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS students (student_id int NOT NULL PRIMARY KEY AUTO_INCREMENT, last_name varchar(30) ,firstname varchar(30))");
            statement.execute("INSERT  INTO newdb.students (student_id,last_name, firstname) VALUES (1,'rocchi', 'main')");
            statement.execute("INSERT INTO newdb.students (student_id,last_name, firstname) VALUES (2,'federici', 'menomain')");
            statement.execute("INSERT INTO newdb.students (student_id,last_name, firstname) VALUES (3,'rossi', 'main un tot')");
            statement.execute("INSERT INTO newdb.students (student_id,last_name, firstname) VALUES (4,'fabrigna', 'rebecca')");

            resultSet = statement.executeQuery("SELECT * FROM students");
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
                students.add(resultSet.getString(3));

            }
            System.out.println(students);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            //statement.execute("ALTER TABLE  students add country VARCHAR(30)");
            statement.execute("UPDATE students SET country = 'italy' WHERE student_id = 1");
            statement.execute("UPDATE students SET country = 'italy' WHERE student_id = 2");
            statement.execute("UPDATE students SET country = 'germany' WHERE student_id = 3");
            statement.execute("UPDATE students SET country = 'germany' WHERE student_id = 4");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

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
            System.out.println("query fatta");

            /*   statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('tommaso', 'main')");
            statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('federico', 'menomain')");
            statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('giulia', 'main un tot')");
            statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('giada', 'lol')"); */

            resultSet = statement.executeQuery("SELECT * FROM newdb.students");
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
                students.add(resultSet.getString(3));
            }

            System.out.println(students);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

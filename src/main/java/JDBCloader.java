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

            /*   statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('rocchi', 'main')");
            statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('federici', 'menomain')");
            statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('rossi', 'main un tot')");
            statement.execute("INSERT INTO newdb.students (last_name, firstname) VALUES ('fabrigna', 'rebecca')"); */

            resultSet = statement.executeQuery("SELECT last_name FROM newdb.students");
            while(resultSet.next()){

                students.add(resultSet.getString(1));

            }
            students.stream().forEach((surname) -> {
                System.out.println(surname);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

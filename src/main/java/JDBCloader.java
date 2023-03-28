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
        ArrayList italan_students = new ArrayList();
        ArrayList german_students = new ArrayList();


/*
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
*/
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            //statement.execute("ALTER TABLE  students add country VARCHAR(30)");
            statement.execute("UPDATE students SET country = 'italy' WHERE student_id = 1");
            statement.execute("UPDATE students SET country = 'italy' WHERE student_id = 2");
            statement.execute("UPDATE students SET country = 'germany' WHERE student_id = 3");
            statement.execute("UPDATE students SET country = 'germany' WHERE student_id = 4");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            statement.execute("CREATE or replace VIEW italian_students AS SELECT last_name, firstname FROM students WHERE country='italy';");
            statement.execute("CREATE or replace VIEW german_students AS SELECT last_name, firstname FROM students WHERE country='germany';");
            resultSet=statement.executeQuery("SELECT * FROM italian_students;");
            while(resultSet.next()){
                String name=resultSet.getString(1);
                String surname=resultSet.getString(2);
                Student stud= new Student(name,surname);
                italan_students.add(stud);
            }
            for (int i=0;i<italan_students.size();i++) {
                System.out.println(italan_students.get(i).toString());
            }
            resultSet=statement.executeQuery("SELECT * FROM german_students;");
            while(resultSet.next()){
                String name=resultSet.getString(1);
                String surname=resultSet.getString(2);
                Student stud= new Student(name,surname);
                german_students.add(stud);
            }
            for (int i=0;i<german_students.size();i++) {
                System.out.println(german_students.get(i).toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

/*
create a class Student that has:
2 string properties:
name
surname
a constructor for setting the 2 values
getters methods
use JDBC for executing the following queries on the jdbc:mysql://localhost:3306/newdb database:
create a view italian_students that gets all the name and surname of the Italian students
create a view german_students that gets all the name and surname of the German students
execute a select using the the italian_students and put the result in an ArrayList of Student objects called italianStudents
execute a select using the the german_students and put the result in an ArrayList of Student objects called germanStudents
 */
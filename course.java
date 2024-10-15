import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class course {

    String courseID;
    String title;
    int credits;
    ArrayList<String> preRequList;
    ArrayList<String> professors;
    ArrayList<String> enrolledStudents;
    int studentCapacity;
    LocalDate dropDeadline;


    public static boolean courseExists(String courseCode) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM courses WHERE course_code = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, courseCode);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if the course exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean  isFull(){
        if((enrolledStudents.size())<studentCapacity){
            return false;
        }
        else{
            return true;
        }
    }

    public int availableSeats(){
        return (studentCapacity-(enrolledStudents.size()));
    }

    public boolean  isDeadlinePassed(){
        return dropDeadline.isBefore(LocalDate.now());
    }

    public void getData(){
        System.out.println("Here is the data about the course :");

        System.out.println("Course ID: "+courseID);
        System.out.println("Course Title: "+title);
        System.out.println("Total credits of the course:"+credits);
        System.out.println("Prerequisite for the course: "+preRequList);
        System.out.println("Student capacity: "+studentCapacity);
        System.out.println("Number of student enrolled: "+enrolledStudents.size());
        System.out.println("professors theaching the course: "+professors);
        System.out.println("List of all the students: "+enrolledStudents);
        System.out.println("Deadlinee to drop the course:"+dropDeadline);

    }

    public String getSpecificData(){
        System.out.println("What specific data do you want ?");
        System.out.println("\n 1.Course ID");
        System.out.println("\n 2.Course title");
        System.out.println("\n 3.Prerequisite to enroll in course");
        System.out.println("\n 4.Credits");
        System.out.println("\n 5.Professors teaching the course");
        System.out.println("\n 6.Student capacity of the course");
        System.out.println("\n 7.Students learnng the course");
        System.out.println("\n 8.Course drop deadline");

        Scanner sc=new Scanner(System.in);

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                return courseID;   
            
            case 2:
                return title;

            case 3:
                return preRequList.toString();
    
            case 4:
                return ""+credits;

            case 5:
                return professors.toString();

            case 6:
                return ""+studentCapacity;

            case 7:
                return enrolledStudents.toString();

            case 8:
                return ""+dropDeadline;

            default:
                return "Invalid input. Please try again.";
        }

    }
}
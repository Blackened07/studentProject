import model.Student;
import model.StudyProfile;
import model.University;

public class App {


    public static void main(String[] args) {
        University university = new University(
                "324234234",
                "Дальневосточный Государственный Технический Университет",
                "ДВГТУ",
                1533,
                StudyProfile.ENGINEERING
        );

        Student student = new Student(
                "Тимофей",
                "324234234",
                47,
                666
        );

        System.out.println(university);
        System.out.println(student);
    }
}

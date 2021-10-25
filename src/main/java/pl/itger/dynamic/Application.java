package pl.itger.dynamic;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }


    /**
     * In this bean, we fill a database with some sample data, and then query the DB using standard crud repository
     * and dynamic projections, this way we can retrieve an object of the desired type reducing the size of the query.
     */
    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, StudentDynamicRepository studentDynamicRepository) {
        return args -> {
            createStudents(studentRepository);
            System.out.println("Count: " + studentRepository.count());

            Iterable<Student> all = studentRepository.findAll();
            all.forEach(System.out::println);

            /*
              Standard way.
              in this case Hibernate will execute the following query:
              select student0_.id as id1_0_0_, student0_.age as age2_0_0_, student0_.email_address as email_ad3_0_0_, student0_.first_name as first_na4_0_0_, student0_.gender as gender5_0_0_, student0_.last_name as last_nam6_0_0_
              from student student0_
              where student0_.id=?
             */
            Optional<Student> student = studentRepository.findById(1L);
            student.ifPresent(System.out::println);


            /*
              Using dynamic projection we can select from DB a limited number of columns.

               Extracting only student last name and email address:
              Hibernate will execute the following query:
              select student0_.last_name as col_0_0_, student0_.email_address as col_1_0_
              from student student0_
              where student0_.id=?
              */
            StudentLnEmailView studentLnEmailView = studentDynamicRepository.findStudentById(1L, StudentLnEmailView.class);
            System.out.println(studentLnEmailView.getLastName() + " " + studentLnEmailView.getEmailAddress());

            /*
              extracting only student age
              Hibernate will execute the following query:
              select student0_.age as col_0_0_
              from student student0_
              where student0_.id=?
              */
            StudentAgeView studentAgeView = studentDynamicRepository.findStudentById(1L, StudentAgeView.class);
            System.out.println(studentAgeView.getAge());

            /*
              in this case, hibernate will select all columns.
             */
            Student s = studentDynamicRepository.findStudentById(1L, Student.class);
            System.out.println(s.getAge());
        };
    }

    /*
     * Inserts some rows into database.
     */
    private void createStudents(StudentRepository studentRepository) {
        Student student1 = new Student("student 1 FN", "Student 1 LN", (short) 18, Student.Gender.MALE, "student_1@abc.edu");
        Student student2 = new Student("student 2 FN", "Student 2 LN", (short) 20, Student.Gender.FEMALE, "student_2@abc.edu");
        Student student3 = new Student("student 3 FN", "Student 3 LN", (short) 21, Student.Gender.FEMALE, "student_3@abc.edu");
        Student student4 = new Student("student 4 FN", "Student 4 LN", (short) 19, Student.Gender.MALE, "student_4@abc.edu");
        List<Student> students = Arrays.asList(student1, student2, student3, student4);
        studentRepository.saveAll(students);
    }

}

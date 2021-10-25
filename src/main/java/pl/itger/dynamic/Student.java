package pl.itger.dynamic;

import javax.persistence.*;

@Entity(name = "student")
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "student_id_gen")
    @SequenceGenerator(name = "student_id_gen",
                       sequenceName = "student_id_seq",
                       allocationSize = 1)
    @Column(name = "id",
            nullable = false)
    private Long id;

    @Column(name = "firstName",
            nullable = false)
    private String firstName;

    @Column(name = "lastName",
            nullable = false)
    private String lastName;

    @Column(name = "age",
            nullable = false)
    private Short age;

    @Column(name = "gender",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "emailAddress",
            nullable = false)
    private String emailAddress;

    public Student() {
    }

    public Student(String firstName, String lastName, Short age, Gender gender, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    enum Gender {
        MALE, FEMALE;
    }
}

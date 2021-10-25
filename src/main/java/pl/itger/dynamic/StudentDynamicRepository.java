package pl.itger.dynamic;

import org.springframework.data.repository.CrudRepository;

/**
 * Declaring a repository method with a Class,
 * we can retrieve an object of the desired type,
 * in our case StudentLnEmailView, StudentAgeView and obviously Student.
 * Example:
 * StudentLnEmailView studentLnEmailView = studentDynamicRepository.findStudentById(1L, StudentLnEmailView.class);
 */
public interface StudentDynamicRepository extends CrudRepository<Student, Long> {
    <T> T findStudentById(Long id, Class<T> type);
}

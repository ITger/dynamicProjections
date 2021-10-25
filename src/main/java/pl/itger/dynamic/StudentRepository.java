package pl.itger.dynamic;

import org.springframework.data.repository.CrudRepository;

/**
 * This is the commonly used repository interface.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}

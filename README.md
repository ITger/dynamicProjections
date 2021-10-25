
This is an example project to illustrate how to use dynamic interface projection in spring CrudRepository.

Using dynamic projections we can retrieve an entity of the desired type reducing the size of the query.

This is a very simple yet powerful example.

The magic happens in the following interface:
```java
public interface StudentDynamicRepository extends CrudRepository<Student, Long> {
    <T> T findStudentById(Long id, Class<T> type);
}
```

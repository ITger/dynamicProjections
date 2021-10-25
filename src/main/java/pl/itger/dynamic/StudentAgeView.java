package pl.itger.dynamic;

/**
 * Interface based Projections.
 * Hibernate will only select one column: age.
 * <p>
 * getters names must exactly match the names of entity properties!!!
 */
public interface StudentAgeView {
    short getAge();
}

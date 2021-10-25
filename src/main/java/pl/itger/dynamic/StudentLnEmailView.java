package pl.itger.dynamic;

/**
 * Interface based Projections.
 * Hibernate will only select two columns: LastName and EmailAddress.
 *
 * getters names must exactly match the names of entity properties!!!
 */
public interface StudentLnEmailView {

    String getLastName();
    String getEmailAddress();
}

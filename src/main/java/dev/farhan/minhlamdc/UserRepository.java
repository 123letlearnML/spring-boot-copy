package dev.farhan.minhlamdc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstname(String firstname); 



    @Query(value = "SELECT * FROM users WHERE SUBSTRING(password, 1, 1) = :kytu", nativeQuery = true)
    List<String> findPasswordsStartingWith(@Param("kytu") String kytu);


//tôi comment code dưới này nhé

//    @Query("SELECT u FROM User u WHERE u.firstname LIKE %:query% OR u.lastname LIKE %:query% OR u.email LIKE %:query%")
//    List<User> searchUser(@Param("query") String query);

    List<User> findByPassword(String password);


    //  @Query("SELECT u FROM User u WHERE u.firstname =:firstname AND u.lastname = :lastname")
    //List<User> findByFirstnameAndLastname(@Param( "firstname") String firstname, @Param("lastname") String lastname);


//    @Query("SELECT u FROM User u WHERE u.lastname LIKE %:lastname%")
//    List<User> findByLastnameStartingWith (String lastname);
}

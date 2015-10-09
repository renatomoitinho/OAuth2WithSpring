package br.inf.audasi.domain.repository;

import br.inf.audasi.domain.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Repository
@Cacheable(value = "UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u")
    Stream<User> streamAllUsers();

    // CRUD method using Optional
    Optional<User> findById(Long id);

    // Query method using Optional

    Optional<User> findByLogin(String name);

}

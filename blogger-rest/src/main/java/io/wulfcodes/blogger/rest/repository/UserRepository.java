package io.wulfcodes.blogger.rest.repository;

import io.wulfcodes.blogger.rest.model.persistent.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("INSERT INTO users (email, username, password) VALUES (:#{#user.email}, :#{#user.username}, :#{#user.password})")
    int insert(@Param("user") User user);

}

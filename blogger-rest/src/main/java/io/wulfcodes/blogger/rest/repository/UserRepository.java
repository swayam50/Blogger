package io.wulfcodes.blogger.rest.repository;

import io.wulfcodes.blogger.rest.model.persistent.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Modifying
    @Query("INSERT INTO users (u_id, u_email, u_username, u_password) VALUES ( :#{#user.id}, :#{#user.email}, :#{#user.username}, :#{#user.password})")
    int insert(@Param("user") User user);

}

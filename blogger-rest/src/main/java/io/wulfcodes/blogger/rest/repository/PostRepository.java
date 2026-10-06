package io.wulfcodes.blogger.rest.repository;

import io.wulfcodes.blogger.rest.model.persistent.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}

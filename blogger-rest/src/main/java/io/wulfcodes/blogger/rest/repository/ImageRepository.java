package io.wulfcodes.blogger.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import io.wulfcodes.blogger.rest.model.persistent.Image;

import java.util.Optional;

@Repository
public interface ImageRepository extends MongoRepository<Image, Integer> {

    @Query("{ 'userId': ?0 }")
    Optional<Image> findByUserId(String userId);

    @Query("{ 'postId': ?0 }")
    Optional<Image> findByPostId(String postId);

}

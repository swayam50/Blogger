package io.wulfcodes.blogger.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import io.wulfcodes.blogger.rest.model.persistent.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, Integer> {}

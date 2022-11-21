package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.ProfilePhoto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfilePhotoRepository extends MongoRepository<ProfilePhoto,String> {
}

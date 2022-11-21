package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.ProfilePhoto;
import com.edumoca.soma.entities.repositories.ProfilePhotoRepository;
import com.edumoca.soma.services.services.ProfilePhotoService;
import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProfilePhotoServiceImpl implements ProfilePhotoService {

    private  final ProfilePhotoRepository profilePhotoRepository;


    @Override
    public String uploadPhoto(String title, MultipartFile uploadPhoto) throws IOException {
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setTitle(title);
        profilePhoto.setPhoto(new Binary(BsonBinarySubType.BINARY,uploadPhoto.getBytes()));
        return profilePhotoRepository.insert(profilePhoto).getId();
    }

    public ProfilePhoto getProfilePhotoData(String id){
        return profilePhotoRepository.findById(id).get();
    }
}

package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.ProfilePhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfilePhotoService {

    public String uploadPhoto(String title, MultipartFile uploadPhoto) throws IOException;
    public ProfilePhoto getProfilePhotoData(String id);

}

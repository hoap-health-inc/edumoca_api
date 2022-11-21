package com.edumoca.soma.entities;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "ProfilePicture")
@Data
public class ProfilePhoto {
    @Id
    private String id;
    private String title;
    private Binary photo;
}

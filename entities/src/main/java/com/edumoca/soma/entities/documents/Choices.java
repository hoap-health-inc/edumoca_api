package com.edumoca.soma.entities.documents;

import lombok.Data;

@Data
//MATCH_THE_FOLLOWING,MULTIPLE_CHOICES,TRUE_FALSE
public class Choices {
    private String text;
    private boolean hasImage;
    private boolean hasText;
    private int generatedImgId;
}

package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class ChapterRequest implements Serializable {
    @JsonIgnore
    private int chapterId;
    @NotBlank(message = "Chapter Name is required")
    private String name;
    private BookIdRequest bookIdRequest;
}

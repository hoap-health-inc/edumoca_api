package com.edumoca.soma.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class TopicRequest implements Serializable {
    @JsonIgnore
    private int topicId;
    @NotBlank(message = "Topic Name is required")
    private String topicName;
    private ChapterIdRequest chapterIdRequest;
}

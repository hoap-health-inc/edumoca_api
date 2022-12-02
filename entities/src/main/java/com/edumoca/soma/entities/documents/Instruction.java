package com.edumoca.soma.entities.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
public class Instruction {
    private String text;
}

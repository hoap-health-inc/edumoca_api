package com.edumoca.soma.entities.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookIdRequest implements Serializable {
    private int bookId;
}

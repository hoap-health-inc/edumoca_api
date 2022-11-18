package com.edumoca.soma.services.beans;

import lombok.Data;

@Data
public class LoadFile {
    private String filename;
    private String fileType;
    private String fileSize;
    private byte[] file;
}

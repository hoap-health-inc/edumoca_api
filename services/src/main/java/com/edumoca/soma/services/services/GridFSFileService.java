package com.edumoca.soma.services.services;

import com.edumoca.soma.services.beans.LoadFile;
import com.mongodb.DBObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GridFSFileService {
    String uploadFile(MultipartFile file, DBObject metadata) throws IOException;
    LoadFile downloadFile(String id) throws IOException;
}

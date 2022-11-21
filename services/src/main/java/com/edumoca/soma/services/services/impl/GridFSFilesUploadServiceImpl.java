package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.services.beans.LoadFile;
import com.edumoca.soma.services.services.GridFSFileService;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class GridFSFilesUploadServiceImpl implements GridFSFileService {
    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperation;
    @Override
    public String uploadFile(MultipartFile uploadFile, DBObject metadata) throws IOException {
        Object fileId = gridFsTemplate.store(uploadFile.getInputStream(),uploadFile.getOriginalFilename()
                ,uploadFile.getContentType(),metadata);
        return fileId.toString();
    }
    public LoadFile downloadFile(String id) throws IOException {
        GridFSFile gridfsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        LoadFile loadFile = new LoadFile();
        if(gridfsFile != null && gridfsFile.getMetadata()!=null) {
            loadFile.setFilename(gridfsFile.getFilename());
            loadFile.setFileType(gridfsFile.getMetadata().get("_contentType").toString());
            loadFile.setFileSize(gridfsFile.getMetadata().get("fileSize").toString());
            loadFile.setFile(IOUtils.toByteArray(gridFsOperation.getResource(gridfsFile).getInputStream()));
        }
        return loadFile;
    }
}

package com.edumoca.soma.services.fileUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperationUtils {

    public static MultipartFile convertFileToMultipartFile(String cellValue) throws IOException {
        File file = new File(cellValue);
        FileItem fileItem = new DiskFileItem("UploadFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
        InputStream input = Files.newInputStream(Paths.get(cellValue));
        OutputStream os = fileItem.getOutputStream();
        IOUtils.copy(input, os);
        return new CommonsMultipartFile(fileItem);
    }

}

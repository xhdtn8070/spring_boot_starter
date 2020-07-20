package org.tikim.boot.service;

import com.amazonaws.AmazonServiceException;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public interface S3Service {
    public String uploadObject(MultipartFile multipartFile) throws IOException;
    public void deleteObject(String date, String savedName, boolean isHard) throws AmazonServiceException;
    public org.springframework.core.io.Resource getObject(String path, String savedName) throws IOException;
}

package org.tikim.boot.serviceImpl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.transaction.annotation.Transactional;
import org.tikim.boot.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Transactional
@Service
public class S3ServiceImpl implements S3Service {
    @Resource
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String bucket;

    @Value("${s3.custom-domain}")
    private String customDomain;

    @Override
    public String uploadObject(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();

        int index = fileName.lastIndexOf(".");
        String fileExt = fileName.substring(index+1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String date = dateFormat.format(new Date());

        UUID uid = UUID.randomUUID();

        String savedName = uid.toString() + "-" + System.currentTimeMillis() + "." + fileExt;

        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentType(multipartFile.getContentType());
        omd.setContentLength(multipartFile.getSize());

        amazonS3.putObject(new PutObjectRequest(bucket+ "/" + date,
                savedName, multipartFile.getInputStream(), omd)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return customDomain + "/" + date + "/" + savedName;
    }

    @Override
    public void deleteObject(String path,String savedName,boolean isHard) throws AmazonServiceException {

        if(isHard){
            amazonS3.deleteObject(new DeleteObjectRequest(bucket+ "/" + path,savedName));
        }else{
            String key = path+"/"+savedName;
            AccessControlList acl = amazonS3.getObjectAcl(bucket,key);
            acl.getGrantsAsList().clear();
            acl.grantPermission(new CanonicalGrantee(acl.getOwner().getId()), Permission.FullControl);
            amazonS3.setObjectAcl(bucket, key, acl);
        }

    }

    public org.springframework.core.io.Resource getObject(String path, String savedName) throws IOException {
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket+"/" + path,savedName));
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);

        org.springframework.core.io.Resource resource = new ByteArrayResource(bytes);
        return resource;
    }

}

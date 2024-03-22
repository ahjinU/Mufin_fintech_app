package com.a502.backend.global.common;

import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3FileUploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public String uploadFile(String filePath, String path) {
        try{
            ClassPathResource resource = new ClassPathResource(filePath);
            String key = path+"/"+resource.getFilename();
            amazonS3Client.putObject(bucket,key,resource.getFile());
            return getUrl(key);
        }catch (Exception e){
            log.error(e.toString());
            throw BusinessException.of(ErrorCode.API_ERROR_INTERNAL_SERVER);
        }
    }

    /**
     * path에 존재하는 파일을 s3에 업로드한다.
     * @param path
     * @return
     */
    public String uploadFile(String path){
        try{
            ClassPathResource resource = new ClassPathResource(path);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(resource.contentLength());
            amazonS3Client.putObject(bucket,path,resource.getInputStream(),metadata);
            return getUrl(path);
        }catch (Exception e){
            log.error(e.toString());
            throw BusinessException.of(ErrorCode.API_ERROR_INTERNAL_SERVER);
        }
    }

    public String uploadFile(MultipartFile file, String path){
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            String key = path+"/"+file.getName();
            amazonS3Client.putObject(bucket,key,file.getInputStream(),metadata);
            return getUrl(key);
        }catch (Exception e){
            throw BusinessException.of(ErrorCode.API_ERROR_INTERNAL_SERVER);
        }
    }

    private String getUrl(String key){
        return amazonS3Client.getUrl(bucket,key).toString();
    }

}

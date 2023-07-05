package com.example.deamaserver.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Util {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String url;

    public void delete(String objectName) {
        if (objectName == null) return;
        amazonS3.deleteObject(bucket, objectName.replace(url+"/", ""));
    }

    public String uploadImage(MultipartFile file) {
        String extension = verificationFile(file);
        String filePath = null;
        try{
            filePath = saveImage(file, extension);
        } catch (IOException e) {
//            throw ImageNotSaveException.EXCEPTION;
        }
        return filePath;
    }

//    public String getS3ObjectUrl(String path) {
//        return url + "/" + path;
//    }

    private String verificationFile(MultipartFile file) {
        if(file == null || file.isEmpty() || file.getOriginalFilename() == null) System.out.println();//throw ImageBadRequestException.EXCEPTION;

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!(extension.contains(".JPG") ||
                extension.contains(".jpg") ||
                extension.contains(".jpeg") ||
                extension.contains(".JPEG") ||
                extension.contains(".png") ||
                extension.contains(".PNG") ||
                extension.contains(".webp") ||
                extension.contains(".WEBP"))
        ) {
//            throw ImageBadRequestException.EXCEPTION;
        }

        return extension;
    }

    private String saveImage(MultipartFile file, String extension) throws IOException {
        String filePath = UUID.randomUUID() + extension;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getBytes().length);
        amazonS3.putObject(new PutObjectRequest(bucket, filePath, file.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return url + "/" + filePath;
    }
}

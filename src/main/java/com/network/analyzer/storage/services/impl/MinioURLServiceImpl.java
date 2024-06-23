package com.network.analyzer.storage.services.impl;

import com.network.analyzer.storage.dtos.Metadata;
import com.network.analyzer.storage.dtos.MinoUploadResponse;
import com.network.analyzer.storage.exceptions.MinioInitiateUploadException;
import com.network.analyzer.storage.models.BucketInfo;
import com.network.analyzer.storage.services.MinioDBService;
import com.network.analyzer.storage.services.MinioURLService;
import com.network.analyzer.storage.utility.enums.PreSignedURLTypes;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MinioURLServiceImpl implements MinioURLService {

    private final MinioClient minioClient;
    private final MinioDBService minioDBService;

    @Value("${minio.expiry}")
    private int expiry;


    public MinioURLServiceImpl(MinioClient minioClient,
                               @Qualifier("minioDBServiceImpl") MinioDBService minioDBService) {
        this.minioClient = minioClient;
        this.minioDBService = minioDBService;
    }

    @Override
    public MinoUploadResponse initiateUpload(String fileName, String layer) {
        try {
            var objectName = UUID.randomUUID().toString();
            var url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(layer)
                            .object(objectName)
                            .expiry(expiry)
                            .build()
            );

            var expiredTime = getExpiredTime();
            var info = new BucketInfo(layer, fileName, url, expiredTime);
            var savedBucketMetadata = minioDBService.save(info);
            var metadata = new Metadata(savedBucketMetadata.getId(),
                    savedBucketMetadata.getFileName(),
                    savedBucketMetadata.getBucketName(),
                    savedBucketMetadata.getCreatedAt(),
                    savedBucketMetadata.getExpiredAt()
            );

            return new MinoUploadResponse(url, PreSignedURLTypes.UPLOAD, metadata);
        } catch (Exception e) {
            throw new MinioInitiateUploadException(
                    String.format("Failed to initiate upload for file: %s in bucketName: %s", fileName, layer), e
            );
        }
    }


    private Date getExpiredTime() {
        // Take current time in milliseconds and add the expiry time in milliseconds
        return new Date(System.currentTimeMillis() + expiry * 1000);
    }
}

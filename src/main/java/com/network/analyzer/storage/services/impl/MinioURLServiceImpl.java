package com.network.analyzer.storage.services.impl;

import com.network.analyzer.storage.dtos.Metadata;
import com.network.analyzer.storage.dtos.MinoUploadResponse;
import com.network.analyzer.storage.exceptions.MinioInitiateUploadException;
import com.network.analyzer.storage.models.BucketInfo;
import com.network.analyzer.storage.services.MinioBucketInfoService;
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

@Service("minioURLServiceImpl")
public class MinioURLServiceImpl implements MinioURLService {

    private final MinioClient minioClient;
    private final MinioDBService minioDBService;
    private final MinioBucketInfoService minioBucketInfoService;

    @Value("${minio.expirationTime}")
    private int expiry;


    public MinioURLServiceImpl(MinioClient minioClient,
                               @Qualifier("minioDBServiceImpl") MinioDBService minioDBService,
                               @Qualifier("minioBucketInfoServiceImpl") MinioBucketInfoService minioBucketInfoService) {
        this.minioClient = minioClient;
        this.minioDBService = minioDBService;
        this.minioBucketInfoService = minioBucketInfoService;
    }

    @Override
    public MinoUploadResponse initiateUpload(String fileName, String bucketName) {
        try {


            if (!minioBucketInfoService.isBucketExists(bucketName)) {
                minioBucketInfoService.createBucket(bucketName);
            }

            var url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(expiry)
                            .build()
            );

            var expiredTime = getExpiredTime();
            var info = new BucketInfo(bucketName, fileName, url, expiredTime);

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
                    String.format("Failed to initiate upload for file: %s in bucketName: %s", fileName, bucketName), e
            );
        }
    }


    private Date getExpiredTime() {
        // Take current time in milliseconds and add the expiry time in milliseconds
        return new Date(System.currentTimeMillis() + expiry * 1000);
    }
}

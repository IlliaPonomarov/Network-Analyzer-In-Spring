package com.network.analyzer.storage.services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("minioBucketInfoServiceImpl")
public class MinioBucketInfoServiceImpl implements MinioBucketInfoService {

    private final MinioClient minioClient;

    @Autowired
    public MinioBucketInfoServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public boolean isBucketExists(String bucketName) {

        try {
            return minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean createBucket(String bucketName) {
        try {
            if (!isBucketExists(bucketName)) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

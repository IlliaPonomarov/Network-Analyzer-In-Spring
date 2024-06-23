package com.network.analyzer.storage.services;

public interface MinioBucketInfoService {
    boolean isBucketExists(String bucketName);
    boolean createBucket(String bucketName);
}

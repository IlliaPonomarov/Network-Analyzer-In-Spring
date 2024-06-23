package com.network.analyzer.storage.dtos;

import java.util.Date;
import java.util.UUID;

public record Metadata (UUID fileId, String fileName, String bucketName, Date createdAt, Date expiredAt) {
    public Metadata {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        if (bucketName == null || bucketName.isBlank()) {
            throw new IllegalArgumentException("Bucket name cannot be null or empty");
        }
    }
}

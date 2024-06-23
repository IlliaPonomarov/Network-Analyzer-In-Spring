package com.network.analyzer.storage.services;

import com.network.analyzer.storage.dto.MinoUploadResponse;

public interface MinioURLService {
    MinoUploadResponse initiateUpload(String fileName, String bucketName);
}

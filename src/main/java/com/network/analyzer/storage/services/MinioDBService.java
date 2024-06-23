package com.network.analyzer.storage.services;

import com.network.analyzer.storage.models.BucketInfo;

public interface MinioDBService {
    BucketInfo save(BucketInfo info);
}

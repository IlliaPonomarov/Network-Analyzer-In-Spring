package com.network.analyzer.storage.services.impl;

import com.network.analyzer.storage.exceptions.MinioDBServiceException;
import com.network.analyzer.storage.models.BucketInfo;
import com.network.analyzer.storage.repos.MinioDBRepository;
import com.network.analyzer.storage.services.MinioDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("minioDBServiceImpl")
@Transactional(readOnly = true)
public class MinioDBServiceImpl implements MinioDBService {

    private final MinioDBRepository minioDBRepository;

    @Autowired
    public MinioDBServiceImpl(MinioDBRepository minioDBRepository) {
        this.minioDBRepository = minioDBRepository;
    }


    @Override
    public BucketInfo save(BucketInfo info) {
        if (info == null) {
            throw new MinioDBServiceException(
                    String.format("Failed to save the bucket info: %s", info)
            );
        }

        return minioDBRepository.save(info);
    }
}

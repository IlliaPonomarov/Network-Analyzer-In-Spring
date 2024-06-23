package com.network.analyzer.storage.repos;

import com.network.analyzer.storage.models.BucketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinioDBRepository extends JpaRepository<BucketInfo, Long> {

}

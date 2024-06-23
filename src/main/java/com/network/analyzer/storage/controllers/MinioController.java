package com.network.analyzer.storage.controllers;

import com.network.analyzer.storage.dtos.MinoUploadResponse;
import com.network.analyzer.storage.services.MinioURLService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minio")
@Tag(name = "Minio Controller", description = "This class is responsible for handling the requests related to the Minio storage.")
public class MinioController {

    private final MinioURLService minioURLService;

    @Autowired
    public MinioController(@Qualifier("minioURLServiceImpl") MinioURLService minioURLService) {
        this.minioURLService = minioURLService;
    }

    @GetMapping("/initiate/upload/{fileName}/bucket/{bucket}")
    public ResponseEntity<MinoUploadResponse> initiateUpload(@PathVariable("fileName") String fileName,
                                                             @PathVariable("bucket") String layer) {

        return ResponseEntity.ok(minioURLService.initiateUpload(fileName, layer));
    }
}

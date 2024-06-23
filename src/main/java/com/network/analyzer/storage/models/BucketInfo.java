package com.network.analyzer.storage.models;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bucket_info")
public class BucketInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "bucket_name")
    private  String bucketName;

    @Column(name = "file_name")
    private  String fileName;

    @Column(name = "file_upload_url")
    private  String fileUploadUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private  Date createdAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private  Date updatedAt;

    @Column(name = "expired_at")
    private  Date expiredAt;

    public BucketInfo(){

    }

    public BucketInfo( String bucketName, String fileName, String fileUploadUrl, Date expiredAt) {
        this.bucketName = bucketName;
        this.fileName = fileName;
        this.fileUploadUrl = fileUploadUrl;
        this.expiredAt = expiredAt;
    }

    public UUID getId() {
        return id;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUploadUrl() {
        return fileUploadUrl;
    }

    public void setFileUploadUrl(String fileUploadUrl) {
        this.fileUploadUrl = fileUploadUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BucketInfo that = (BucketInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(bucketName, that.bucketName) && Objects.equals(fileName, that.fileName) && Objects.equals(fileUploadUrl, that.fileUploadUrl) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(expiredAt, that.expiredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bucketName, fileName, fileUploadUrl, createdAt, updatedAt, expiredAt);
    }

    @Override
    public String toString() {
        return "BucketInfo{" +
                "id=" + id +
                ", bucketName='" + bucketName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileUploadUrl='" + fileUploadUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", expiredAt='" + expiredAt + '\'' +
                '}';
    }
}

package com.example.networkanalyzerspringboot.config;

import com.example.networkanalyzerspringboot.utility.FolderConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@ConfigurationProperties(prefix = "storage")
public class StorageConfig {

    /**
     * Folder location for storing files
     */
    private String location = FolderConstants.PCAPS_FOLDER;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

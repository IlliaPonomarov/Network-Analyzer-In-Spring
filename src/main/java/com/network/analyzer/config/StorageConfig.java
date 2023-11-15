package com.network.analyzer.config;

import com.network.analyzer.utility.FolderConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

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

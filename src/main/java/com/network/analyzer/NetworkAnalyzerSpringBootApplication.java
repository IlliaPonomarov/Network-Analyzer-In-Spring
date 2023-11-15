package com.network.analyzer;

import com.network.analyzer.config.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class NetworkAnalyzerSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkAnalyzerSpringBootApplication.class, args);
    }

}

package com.network.analyzer.storage.services;


import com.network.analyzer.config.StorageConfig;
import com.network.analyzer.storage.exceptions.StorageException;
import com.network.analyzer.storage.services.impl.FileSystemStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/***
 * @author Illia Ponomarov
 * This class is responsible for testing FileSystemStorageService class
 * @see FileSystemStorageService
 */

@ExtendWith(MockitoExtension.class)
public class FileSystemStorageServiceTest {

    @Mock
    private FileSystemStorageService fileSystemStorageService;

    @BeforeEach
    public void setUp() {
        try {
            fileSystemStorageService = new FileSystemStorageService(new StorageConfig());
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     * @since  throw StorageException when file does not exist or is empty
     */

    @Test
    public void fileDoesNotExistStoreTest() {
        var multipartFile = mock(MultipartFile.class);
        when(multipartFile.isEmpty()).thenReturn(true);

        assertThrows(StorageException.class, () -> fileSystemStorageService.store(multipartFile));
    }

    /***
     * @since  throw StorageException when file does not exist or is empty
     */

    @Test
    @Disabled
    public void invalidDestinationPathStoreTest() {
        var multipartFile = mock(MultipartFile.class);
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.pcap");

        // input stream as random bytes
        try {
            when(multipartFile.getInputStream()).thenReturn(new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        Path destinationFilePath = Path.of("test.pcap");

        when(fileSystemStorageService.getRootLocation()).thenReturn("../");

        assertThrows(StorageException.class, () -> fileSystemStorageService.store(multipartFile));
    }



}

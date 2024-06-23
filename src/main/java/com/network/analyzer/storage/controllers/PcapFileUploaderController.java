package com.network.analyzer.storage.controllers;

import com.network.analyzer.services.logger.LoggerService;
import com.network.analyzer.storage.exceptions.StorageException;
import com.network.analyzer.storage.services.impl.PcapFileUploader;
import com.network.analyzer.storage.services.StorageService;
import com.network.analyzer.utility.FolderConstants;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is responsible for handling the requests related to the pcap file uploading.
 * @version 1.0
 * @since 1.0
 * @author Illia Ponomarov
 */
@RestController
@RequestMapping("/pcap-file")
public class PcapFileUploaderController {
    private final HttpServlet httpServlet;
    private StorageService storageService;


    /**
     * Constructor
     * @param httpServlet HttpServlet
     * @param storageService StorageService
     *  The service that is responsible for storing the pcap files.
     */

    @Autowired
    public PcapFileUploaderController(HttpServlet httpServlet, StorageService storageService) {
        this.httpServlet = httpServlet;
        this.storageService = storageService;
    }

    /***
     * This method is responsible for uploading the pcap file.
     * @param pcap MultipartFile - the pcap file that is going to be uploaded.
     * @return ResponseEntity- the response entity that contains the message about the result of the operation.
     */
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> uploadPcapFile(@RequestPart("pcap") MultipartFile pcap) {
        var hashFileName = "";

        LoggerService.info("Uploading pcap file");
        try {
            hashFileName = this.storageService.store(pcap);
            LoggerService.info(String.format("File %s uploaded successfully", pcap.getOriginalFilename()));

            Stream<Path> pathStream = this.storageService.loadAll();
            LoggerService.info("Files in the storage:");
            LoggerService.info("====================================");
            pathStream.forEach(path -> LoggerService.info(path.getFileName().toString()));
            LoggerService.info("====================================");
        } catch (StorageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (pcap == null)
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<String>(
                String.format("File %s uploaded successfully", hashFileName), HttpStatus.OK);
    }



    @GetMapping("/get-files")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<String>> getFiles() {
        String path = httpServlet.getServletContext().getRealPath(FolderConstants.PCAPS_FOLDER);
        PcapFileUploader pcapFileUploader = new PcapFileUploader(null, path, httpServlet.getServletContext().getRealPath(FolderConstants.PCAPS_FOLDER));


        return new ResponseEntity<>(pcapFileUploader.getFiles(), HttpStatus.OK);
    }
}

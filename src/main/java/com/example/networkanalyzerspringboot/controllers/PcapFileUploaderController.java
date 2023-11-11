package com.example.networkanalyzerspringboot.controllers;

import com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions.StorageException;
import com.example.networkanalyzerspringboot.services.PcapFileUploader;
import com.example.networkanalyzerspringboot.services.StorageService;
import com.example.networkanalyzerspringboot.utility.FolderConstants;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/pcap-file")
public class PcapFileUploaderController {
    private final HttpServlet httpServlet;
    private StorageService storageService;

    @Autowired
    public PcapFileUploaderController(HttpServlet httpServlet, StorageService storageService) {
        this.httpServlet = httpServlet;
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public void uploadPcapFile(@RequestPart("pcap") MultipartFile pcap) {
        String path = httpServlet.getServletContext().getRealPath(FolderConstants.PCAPS_FOLDER);

        try {
            this.storageService.store(pcap);
            Stream<Path> pathStream = this.storageService.loadAll();
            pathStream.forEach(System.out::println);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
        if (pcap == null)
            throw new MultipartException("No pcap file provided");

    }

    @GetMapping("/get-packets")
    @ResponseStatus(HttpStatus.OK)
    public void getPackets(@RequestParam("id") String id) {

    }

    @GetMapping("/get-files")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getFiles() {
        String path = httpServlet.getServletContext().getRealPath(FolderConstants.PCAPS_FOLDER);
        PcapFileUploader pcapFileUploader = new PcapFileUploader(null, path, httpServlet.getServletContext().getRealPath(FolderConstants.PCAPS_FOLDER));


        return pcapFileUploader.getListOfFiles();
    }
}

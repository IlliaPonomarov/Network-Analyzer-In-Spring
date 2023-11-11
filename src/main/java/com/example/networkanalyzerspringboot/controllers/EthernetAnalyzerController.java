package com.example.networkanalyzerspringboot.controllers;

import com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions.StorageFileNotFoundException;
import com.example.networkanalyzerspringboot.models.Ethernet;
import com.example.networkanalyzerspringboot.services.EthernetService;
import com.example.networkanalyzerspringboot.services.PcapFileUploader;
import com.example.networkanalyzerspringboot.services.StorageService;
import com.example.networkanalyzerspringboot.utility.FolderConstants;
import jakarta.servlet.http.HttpServlet;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ethernet")
public class EthernetAnalyzerController {

    private final HttpServlet httpServlet;
    private StorageService storageService;
    private EthernetService ethernetService;

    @Autowired
    public EthernetAnalyzerController(StorageService storageService, HttpServlet httpServlet, EthernetService ethernetService) {
        this.storageService = storageService;
        this.httpServlet = httpServlet;
        this.ethernetService = ethernetService;
    }

    @GetMapping("/")
    public List<Ethernet> physicalLayer(@RequestParam String id) {
        String filename = String.format("%s", id);
        List<Packet> packets = new ArrayList<>();

        if (id == null)
            throw new RuntimeException("No pcap file provided");

        try {
            packets = storageService.loadPackets(filename);

            if (packets.isEmpty())
                throw new RuntimeException("No packets found");
            this.ethernetService.setPackets(packets);

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this.ethernetService.getEthernetList();
    }
}

package com.network.analyzer.layers.physical.controllers;

import com.network.analyzer.layers.physical.exceptions.EthernetPacketsNotFoundException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.layers.physical.models.Ethernet;
import com.network.analyzer.layers.physical.services.EthernetService;
import com.network.analyzer.storage.services.StorageService;
import com.network.analyzer.utility.validators.MacValidation;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ethernet")
public class EthernetAnalyzerController {
    private final StorageService storageService;
    private final EthernetService ethernetService;

    @Autowired
    public EthernetAnalyzerController(StorageService storageService, EthernetService ethernetService) {
        this.storageService = storageService;
        this.ethernetService = ethernetService;
    }

    @GetMapping("/")
    public List<Ethernet> findAll(@RequestParam String id) {
        String filename = String.format("%s", id);
        List<Packet> packets = new ArrayList<>();

        if (id == null || id.isEmpty())
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

    @GetMapping("/{source-mac}/source-mac")
    public List<Ethernet> findBySourceMac(@PathVariable("source-mac") @MacValidation String sourceMac,  @RequestParam("filename") String filename) {

        if (sourceMac == null || sourceMac.isEmpty())
            throw new RuntimeException("No source mac provided");

        if (filename == null || filename.isEmpty())
            throw new RuntimeException("No pcap file provided");


        try {
            List<Packet> packets = this.storageService.loadPackets(filename);
            this.ethernetService.setPackets(packets);

            if (packets.isEmpty())
                throw new StorageFileNotFoundException("No packets found");

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }


        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new EthernetPacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterBySourceMac(sourceMac);
    }

    @GetMapping("/{destination-mac}/destination-mac")
    public List<Ethernet> findByDestinationMac(@PathVariable("destination-mac") @MacValidation String destinationMac, @RequestParam("filename") String filename) {

        if (destinationMac == null || destinationMac.isEmpty())
            throw new RuntimeException("No destination mac provided");

        if (filename == null || filename.isEmpty())
            throw new RuntimeException("No pcap file provided");

        try {
            List<Packet> packets = this.storageService.loadPackets(filename);
            this.ethernetService.setPackets(packets);

            if (packets.isEmpty())
                throw new StorageFileNotFoundException("No packets found");

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new EthernetPacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterByDestinationMac(destinationMac);
    }

    @GetMapping("/{source-mac}/source-mac/{destination-mac}/destination-mac")
    public List<Ethernet> findBySourceAndDestinationMac(@PathVariable("source-mac") @MacValidation String sourceMac, @PathVariable("destination-mac") @MacValidation String destinationMac, @RequestParam("filename") String filename) {

        if (sourceMac == null || sourceMac.isEmpty())
            throw new RuntimeException("No source mac provided");

        if (destinationMac == null || destinationMac.isEmpty())
            throw new RuntimeException("No destination mac provided");

        if (filename == null || filename.isEmpty())
            throw new RuntimeException("No pcap file provided");

        try {
            List<Packet> packets = this.storageService.loadPackets(filename);
            this.ethernetService.setPackets(packets);

            if (packets.isEmpty())
                throw new StorageFileNotFoundException("No packets found");

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new EthernetPacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterBySourceAndDestinationMac(sourceMac, destinationMac);
    }

    @GetMapping("/{ethernet-type}/ethernet-type")
    public List<Ethernet> findByEthernetType(@PathVariable("ethernet-type") String ethernetType, @RequestParam("filename") String filename) {

        if (ethernetType == null || ethernetType.isEmpty())
            throw new RuntimeException("No ethernet type provided");

        if (filename == null || filename.isEmpty())
            throw new RuntimeException("No pcap file provided");

        try {
            List<Packet> packets = this.storageService.loadPackets(filename);
            this.ethernetService.setPackets(packets);

            if (packets.isEmpty())
                throw new StorageFileNotFoundException("No packets found");

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new EthernetPacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterByEthernetType(ethernetType);
    }


}

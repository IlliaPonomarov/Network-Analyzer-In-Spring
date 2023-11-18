package com.network.analyzer.layers.network.controllers;

import com.network.analyzer.layers.network.exceptions.ARPPacketsNotFoundException;
import com.network.analyzer.layers.network.models.ARP;
import com.network.analyzer.layers.network.services.InternetProtocolService;
import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.storage.services.StorageService;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/network/{id}/arp")
public class ARPController {

    private final StorageService storageService;
    private final InternetProtocolService ipService;

    @Autowired
    public ARPController(StorageService storageService, @Qualifier("internetProtocolService") InternetProtocolService ipService) {
        this.storageService = storageService;
        this.ipService = ipService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findARPs(@PathVariable("id") String id) {
        List<ARP> arps = new ArrayList<>();
        try {
            List<Packet> packets = storageService.loadPackets(id);
            ipService.setPackets(packets);
        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }
        arps = ipService.findARPs();

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return arps;
    }
}

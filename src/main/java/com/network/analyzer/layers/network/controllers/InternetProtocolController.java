package com.network.analyzer.layers.network.controllers;

import com.network.analyzer.layers.network.exceptions.ICMPPacketsNotFoundException;
import com.network.analyzer.layers.network.models.ICMP;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.services.InternetProtocolService;
import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.storage.services.StorageService;
import com.network.analyzer.utility.validators.network.VersionValidation;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/network")
public class InternetProtocolController {

    private final StorageService storageService;
    private final InternetProtocolService ipService;

    @Autowired
    public InternetProtocolController(StorageService storageService, @Qualifier("internetProtocolService") InternetProtocolService ipService) {
        this.storageService = storageService;
        this.ipService = ipService;
    }

    @GetMapping("/{version}/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<InternetProtocolV4> findPacketsByIPVersion(@VersionValidation @PathVariable("version") String version , @PathVariable("id") String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);
            ipService.setPackets(packets);
        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }

        return ipService.findInternetProtocolsByIPVersion(String.format("IPv%s", version));
    }

    @GetMapping("/icmp/{version}/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ICMP> findICMPsByIPVersion(@VersionValidation @PathVariable("version") String version , @PathVariable("id") String id) {
        List<ICMP> icmps = new ArrayList<>();
        String format = String.format("IPv%s", version);
        try {
            List<Packet> packets = storageService.loadPackets(id);
            ipService.setPackets(packets);
        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }

        if (version.equals("4"))
            icmps = ipService.findICMPv4sByIPVersion(String.format(format));

        if (version.equals("6"))
            icmps = ipService.findICMPv6sByIPVersion(String.format(format));

        if (icmps.isEmpty())
            throw new ICMPPacketsNotFoundException(String.format("ICMPv%s packets not found", format));

        return ipService.findICMPv6sByIPVersion(String.format(format));
    }
}

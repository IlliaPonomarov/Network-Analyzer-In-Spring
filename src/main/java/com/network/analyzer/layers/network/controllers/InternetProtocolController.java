package com.network.analyzer.layers.network.controllers;

import com.network.analyzer.layers.network.exceptions.IPPacketsNotFoundException;
import com.network.analyzer.layers.network.models.InternetProtocol;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.services.InternetProtocolService;
import com.network.analyzer.services.FilterIPService;
import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.storage.services.StorageService;
import com.network.analyzer.utility.validators.network.VersionValidation;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network/{id}/ip/{version}")
public class InternetProtocolController {

    private final StorageService storageService;
    private final InternetProtocolService ipService;
    private final FilterIPService filterIPService;

    @Autowired
    public InternetProtocolController(StorageService storageService, @Qualifier("internetProtocolServiceImpl") InternetProtocolService ipService, @Qualifier("filterIPServiceImpl") FilterIPService filterIPService) {
        this.storageService = storageService;
        this.ipService = ipService;
        this.filterIPService = filterIPService;
    }

    @GetMapping("/")
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

    @GetMapping("/{sourceIp}/between/{destinationIp}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<? extends InternetProtocol> findPacketsBySourceAndDestination(@PathVariable("sourceIp") String sourceIp, @PathVariable("destinationIp") String destinationIp, @VersionValidation @PathVariable("version") String version, @PathVariable("id") String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);
            filterIPService.setPackets(packets);
        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }

        List<? extends InternetProtocol> internetProtocols = (List<? extends InternetProtocol>) filterIPService.filterBySourceAndDestination(sourceIp, destinationIp, String.format("IPv%s", version));

        if (internetProtocols.isEmpty())
            throw new IPPacketsNotFoundException(String.format("IP packets with source ip: %s and destination ip: %s not found", sourceIp, destinationIp));

        return internetProtocols;

    }

    @GetMapping("/{sourceIp}/source-ip")
    @ResponseStatus(HttpStatus.FOUND)
    public List<InternetProtocolV4> findPacketsBySource(@PathVariable("sourceIp") String sourceIp, @VersionValidation @PathVariable("version") String version, @PathVariable("id") String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);
            filterIPService.setPackets(packets);
        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }

        List<? extends InternetProtocol> internetProtocols = (List<? extends InternetProtocol>) filterIPService.filterBySource(sourceIp, String.format("IPv%s", version));

        if (internetProtocols.isEmpty())
            throw new IPPacketsNotFoundException(String.format("IP packets with source ip: %s not found", sourceIp));

        return (List<InternetProtocolV4>) internetProtocols;
    }

    @GetMapping("/{destinationIp}/destination-ip")
    @ResponseStatus(HttpStatus.FOUND)
    public List<InternetProtocolV4> findPacketsByDestination(@PathVariable("destinationIp") String destinationIp, @VersionValidation @PathVariable("version") String version, @PathVariable("id") String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);
            filterIPService.setPackets(packets);
        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }

        List<? extends InternetProtocol> internetProtocols = (List<? extends InternetProtocol>) filterIPService.filterByDestination(destinationIp, String.format("IPv%s", version));

        if (internetProtocols.isEmpty())
            throw new IPPacketsNotFoundException(String.format("IP packets with destination ip: %s not found", destinationIp));

        return (List<InternetProtocolV4>) internetProtocols;
    }


}

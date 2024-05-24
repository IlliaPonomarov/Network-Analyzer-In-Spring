package com.network.analyzer.layers.network.controllers;

import com.network.analyzer.layers.network.exceptions.ARPPacketsNotFoundException;
import com.network.analyzer.layers.network.exceptions.HardwareTypeIsEmptyException;
import com.network.analyzer.layers.network.models.ARP;
import com.network.analyzer.layers.network.services.ARPServiceImpl;
import com.network.analyzer.layers.network.services.InternetProtocolService;
import com.network.analyzer.services.FilterIPService;
import com.network.analyzer.services.FilterMACService;
import com.network.analyzer.services.PacketService;
import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.storage.services.StorageService;
import com.network.analyzer.utility.validators.ethernet.MacValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/network/{id}/arp")
public class ARPController {

    private final StorageService storageService;
    private final InternetProtocolService ipService;

    private final FilterIPService filterIPService;
    private final FilterMACService filterMACService;
    private final PacketService packetService;
    private final ARPServiceImpl arpServiceImpl;

    @Autowired
    public ARPController(StorageService storageService,
                         @Qualifier("internetProtocolServiceImpl") InternetProtocolService ipService,
                         @Qualifier("filterARPServiceImpl") FilterIPService filterIPService,
                         @Qualifier("filterARPServiceImpl") FilterMACService filterMACService,
                         @Qualifier("arpServiceImpl") ARPServiceImpl arpServiceImpl,
                         @Qualifier("arpServiceImpl") PacketService packetService
    ) {
        this.storageService = storageService;
        this.ipService = ipService;
        this.filterIPService = filterIPService;
        this.filterMACService = filterMACService;
        this.packetService = packetService;
        this.arpServiceImpl = arpServiceImpl;
    }


    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<List<ARP>> findARPs(@PathVariable("id") String id) {
        List<ARP> arps = new ArrayList<>();

        this.setPackets(id);
        arps = arpServiceImpl.findARPs();

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return new ResponseEntity<>(arps, HttpStatus.FOUND);
    }

    @GetMapping("{opcode}/opcode")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findARPsByOpcode(@PathVariable("opcode") String opcode,
                                      @PathVariable("id") String id) {

        List<ARP> arps = new ArrayList<>();

        if (opcode.isEmpty())
            throw new HardwareTypeIsEmptyException("Opcode is empty");

        this.setPackets(id);
        arps = arpServiceImpl.findARPsByOpcode(opcode.toLowerCase());

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return arps;
    }

    @GetMapping("/{protocol_type}/protocol-type")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findARPsByProtocolType(@PathVariable("protocol_type") String protocolType,
                                            @PathVariable("id") String id) {

        List<ARP> arps = new ArrayList<>();

        if (protocolType.isEmpty())
            throw new HardwareTypeIsEmptyException("Protocol type is empty");

        this.setPackets(id);
        arps = arpServiceImpl.findARPsByProtocolType(protocolType.toLowerCase());

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return arps;
    }

    @GetMapping("/{hardware_type}/hardware-type")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findARPsByHardwareType(@PathVariable("hardware_type") String hardwareType,
                                            @PathVariable("id") String id) {

        List<ARP> arps = new ArrayList<>();

        if (hardwareType.isEmpty())
            throw new HardwareTypeIsEmptyException("Hardware type is empty");

        this.setPackets(id);
        arps = arpServiceImpl.findARPsByHardwareType(hardwareType.toLowerCase());

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return arps;
    }


    @GetMapping("/{source-mac}/source-mac")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findARPsBySourceMacAddress(@MacValidation @PathVariable("source-mac") String macAddress,
                                          @PathVariable("id") String id) {

        List<ARP> arps = new ArrayList<>();

        if (macAddress.isEmpty())
            throw new HardwareTypeIsEmptyException("Mac address is empty");

        this.setPackets(id);
        arps = (List<ARP>) filterMACService.filterBySourceMacAddress(macAddress.toLowerCase());

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return arps;
    }

    @GetMapping("/{destination-mac}/destination-mac")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findARPsByDestinationMacAddress(@MacValidation @PathVariable("destination-mac") String macAddress,
                                          @PathVariable("id") String id) {

        List<ARP> arps = new ArrayList<>();

        if (macAddress.isEmpty())
            throw new HardwareTypeIsEmptyException("Mac address is empty");

        this.setPackets(id);
        arps = (List<ARP>) filterMACService.filterByDestinationMacAddress(macAddress.toLowerCase());

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException("ARP packets not found");

        return arps;
    }

    @GetMapping("/{sourceIp}/between/{destinationIp}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ARP> findPacketsBySourceAndDestination(@PathVariable("sourceIp") String sourceIp,
                                                       @PathVariable("destinationIp") String destinationIp,
                                                       @PathVariable("id") String id) {
        this.setPackets(id);

        List<ARP> arps = (List<ARP>) filterIPService.filterBySourceAndDestination(sourceIp, destinationIp, "arp");

        if (arps.isEmpty())
            throw new ARPPacketsNotFoundException(String.format("ARP packets with source ip: %s and destination ip: %s not found", sourceIp, destinationIp));

        return arps;

    }

    private void setPackets(String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);
            packetService.setPackets(packets);
            filterMACService.setPackets(packets);

        } catch (StorageFileNotFoundException e) {
            throw new PacketListIsEmptyException("Packet list is empty");
        }
    }
}

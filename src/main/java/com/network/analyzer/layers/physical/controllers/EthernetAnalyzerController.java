package com.network.analyzer.layers.physical.controllers;

import com.network.analyzer.layers.physical.exceptions.PacketsNotFoundException;
import com.network.analyzer.layers.physical.exceptions.ParamWasNotFoundException;
import com.network.analyzer.storage.exceptions.StorageException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.layers.physical.models.Ethernet;
import com.network.analyzer.layers.physical.services.EthernetService;
import com.network.analyzer.storage.services.StorageService;
import com.network.analyzer.utility.validators.ethernet.MacValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/***
 * This class is responsible for handling the requests related to the ethernet packets.
 * @version 1.0
 * @since 1.0
 * @author Illia Ponomarov
 */
@RestController
@RequestMapping("/ethernet/{id}")
public class EthernetAnalyzerController {
    private final StorageService storageService;
    private final EthernetService ethernetService;

    @Autowired
    public EthernetAnalyzerController(StorageService storageService, EthernetService ethernetService) {
        this.storageService = storageService;
        this.ethernetService = ethernetService;
    }

    /***
     * This method is responsible for finding all the ethernet packets in the pcap file.
     * @param id String - the id of the pcap file.
     * @return List<Ethernet> - the list of the ethernet packets.
     */

    @Operation(summary = "Find all ethernet packets in the pcap file")
    @ApiResponse(responseCode = "200", description = "List of ethernet packets",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Ethernet.class))
    })

    @ApiResponse(responseCode = "404", description = "No packets found",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PacketsNotFoundException.class))
    })

    @ApiResponse(responseCode = "500", description = "No pcap file provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StorageException.class))
    })
    @GetMapping("/")
    public List<Ethernet> findAll(@PathVariable String id) throws StorageException {
        String filename = String.format("%s", id);
        List<Packet> packets = new ArrayList<>();

        var params = Collections.singletonMap("id", id);

        verifyParams(params);

        try {
            packets = storageService.loadPackets(filename);

            if (packets.isEmpty())
                throw new PacketsNotFoundException("No packets found");

            this.ethernetService.setPackets(packets);

        } catch (StorageFileNotFoundException e) {
            throw new StorageFileNotFoundException(e.getMessage());
        }
        return this.ethernetService.getEthernetList();
    }

    /***
     * This method is responsible for finding all the ethernet packets by the source mac address.
     * @param sourceMac String - the source mac address.
     * @param id String - the id of the pcap file.
     * @return List<Ethernet> - the list of the ethernet packets.
     */

    @Operation(summary = "Find all ethernet packets by source mac address")
    @ApiResponse(responseCode = "200", description = "List of ethernet packets",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Ethernet.class))
    })
    @ApiResponse(responseCode = "404", description = "No ethernet packets found",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PacketsNotFoundException.class))
    })

    @ApiResponse(responseCode = "500", description = "No pcap file provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StorageException.class))
    })
    @ApiResponse(responseCode = "500", description = "No source mac provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class))
    })
    @ApiResponse(responseCode = "500", description = "No packets found",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StorageFileNotFoundException.class))
    })

    @GetMapping("/{source-mac}/source-mac")
    public List<Ethernet> findBySourceMac(@PathVariable("source-mac") @MacValidation String sourceMac,  @PathVariable("id") String id) {

        var params = Map.ofEntries(
            Map.entry("sourceMac", sourceMac),
            Map.entry("id", id)
        );

        verifyParams(params);

        try {
            List<Packet> packets = this.storageService.loadPackets(id);
            this.ethernetService.setPackets(packets);

            if (packets.isEmpty())
                throw new StorageFileNotFoundException("No packets found");

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }


        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new PacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterBySourceMac(sourceMac);
    }


    /***
     * This method is responsible for finding all the ethernet packets by the destination mac address.
     * @param destinationMac String - the destination mac address.
     * @param id String - the id of the pcap file.
     * @return List<Ethernet> - the list of the ethernet packets.
     */
    @Operation(summary = "Find all ethernet packets by destination mac address")
    @ApiResponse(responseCode = "200", description = "List of ethernet packets",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Ethernet.class))
    })
    @ApiResponse(responseCode = "404", description = "No ethernet packets found",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PacketsNotFoundException.class))
    })
    @ApiResponse(responseCode = "500", description = "No pcap file provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StorageException.class))
    })

    @GetMapping("/{destination-mac}/destination-mac")
    public List<Ethernet> findByDestinationMac(@PathVariable("destination-mac") @MacValidation String destinationMac, @PathVariable("id") String id) {

        var params = Map.ofEntries(
            Map.entry("destinationMac", destinationMac),
            Map.entry("id", id)
        );

        verifyParams(params);

        try {
            List<Packet> packets = this.storageService.loadPackets(id);
            this.ethernetService.setPackets(packets);

            if (packets.isEmpty())
                throw new StorageFileNotFoundException("No packets found");

        } catch (StorageFileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new PacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterByDestinationMac(destinationMac);
    }

    /***
     * This method is responsible for finding all the ethernet packets by the source and destination mac address.
     * @param sourceMac String - the source mac address.
     * @param destinationMac String - the destination mac address.
     * @param id String - the id of the pcap file.
     * @return List<Ethernet> - the list of the ethernet packets.
     */
    @Operation(summary = "Find all ethernet packets by source and destination mac address")
    @ApiResponse(responseCode = "200", description = "List of ethernet packets",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Ethernet.class))
    })
    @ApiResponse(responseCode = "404", description = "No ethernet packets found",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PacketsNotFoundException.class))
    })
    @ApiResponse(responseCode = "500", description = "No pcap file provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StorageException.class))
    })
    @ApiResponse(responseCode = "500", description = "No source mac provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class))
    })

    @GetMapping("/{source-mac}/source-mac/{destination-mac}/destination-mac")
    public List<Ethernet> findBySourceAndDestinationMac(@PathVariable("source-mac") @MacValidation String sourceMac, @PathVariable("destination-mac") @MacValidation String destinationMac, @PathVariable("id") String id) {

        var params = Map.ofEntries(
            Map.entry("sourceMac", sourceMac),
            Map.entry("destinationMac", destinationMac),
            Map.entry("id", id)
        );

        verifyParams(params);

        List<Packet> packets = this.getPackets(id);

        if (packets.isEmpty())
            throw new PacketsNotFoundException("No packets found");

        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new PacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterBySourceAndDestinationMac(sourceMac, destinationMac);
    }

    /***
     * This method is responsible for finding all the ethernet packets by the ethernet type.
     * @param ethernetType String - the ethernet type.
     * @param id String - the id of the pcap file.
     * @return List<Ethernet> - the list of the ethernet packets.
     */

    @Operation(summary = "Find all ethernet packets by ethernet type")
    @ApiResponse(responseCode = "200", description = "List of ethernet packets",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Ethernet.class))
    })
    @ApiResponse(responseCode = "404", description = "No ethernet packets found",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PacketsNotFoundException.class))
    })
    @ApiResponse(responseCode = "500", description = "No pcap file provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StorageException.class))
    })
    @ApiResponse(responseCode = "500", description = "No ethernet type provided",
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RuntimeException.class))
    })

    @GetMapping("/{ethernet-type}/ethernet-type")
    public List<Ethernet> findByEthernetType(@PathVariable("ethernet-type") String ethernetType, @PathVariable("id") String id) {

        var params = Map.ofEntries(
            Map.entry("ethernetType", ethernetType),
            Map.entry("id", id)
        );

        verifyParams(params);

        List<Packet> packets = this.getPackets(id);

        if (packets.isEmpty())
            throw new PacketsNotFoundException("No packets found");

        List<Ethernet> ethernets = this.ethernetService.getEthernetList();

        if (ethernets.isEmpty())
            throw new PacketsNotFoundException("No ethernet packets found");

        return this.ethernetService.filterByEthernetType(ethernetType);
    }


    private void verifyParams(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty())
                throw new ParamWasNotFoundException(String.format("No %s provided", entry.getKey()));
        }
    }


    private List<Packet> getPackets(String id) {
        try {
            List<Packet> packets = storageService.loadPackets(id);

            if (packets.isEmpty())
                throw new PacketsNotFoundException("No packets found");


            if (this.ethernetService.getPackets().isEmpty())
                this.ethernetService.setPackets(packets);

            return this.ethernetService.getPackets();

        } catch (StorageFileNotFoundException e) {
            throw new PacketsNotFoundException("No packets found");
        }
    }


}

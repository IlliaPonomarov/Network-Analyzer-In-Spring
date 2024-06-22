package com.network.analyzer.ddos.controllers;


import com.network.analyzer.ddos.dtos.DdosIPv4DTO;
import com.network.analyzer.services.DDOSService;
import com.network.analyzer.storage.exceptions.StorageException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import com.network.analyzer.storage.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Set;

@RestController
@RequestMapping("/network/{id}/ddos")
public class DDOSController {

    private final StorageService storageService;
    private final DDOSService ddosService;

    @Autowired
    public DDOSController(StorageService storageService, @Qualifier("ddosServiceImpl") DDOSService ddosService) {
        this.storageService = storageService;
        this.ddosService = ddosService;
    }

    @GetMapping("/udp")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<LinkedHashMap<String, Set<DdosIPv4DTO>>> findDDOSUDPPackets(@PathVariable("id") String id) throws StorageException {
        try {
            var packets = storageService.loadPackets(id);
            ddosService.setPackets(packets);
            var udps = ddosService.getDDOSUDPPackets();


            if (udps.isEmpty())
                return ResponseEntity.notFound().build();


            return ResponseEntity.ok(udps);

        } catch (StorageFileNotFoundException e) {
            throw new StorageException(
                    String.format("Could not find packets for network with id: %s", id)
            );
        }

    }
}

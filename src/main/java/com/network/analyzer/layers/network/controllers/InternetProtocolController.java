package com.network.analyzer.layers.network.controllers;

import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.services.InternetProtocolServiceImpl;
import com.network.analyzer.storage.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/network")
public class InternetProtocolController {

    private final StorageService storageService;
    private final InternetProtocolServiceImpl ipService;


    @GetMapping("/{version}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<InternetProtocolV4> findPacketsByIPVersion(@Valid @PathVariable("version") String version) {
        return null;
    }
}

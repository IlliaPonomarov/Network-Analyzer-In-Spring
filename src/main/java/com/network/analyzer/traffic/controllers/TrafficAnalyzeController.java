package com.network.analyzer.traffic.controllers;


import com.network.analyzer.layers.physical.models.Ethernet;
import com.network.analyzer.traffic.services.TrafficLayerAnalyzerService;
import com.network.analyzer.utility.FolderConstants;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/traffic-analyze/layer")
public class TrafficAnalyzeController {

    private final TrafficLayerAnalyzerService trafficLayerAnalyzerService;
    private final HttpServlet httpServlet;

    @Autowired
    public TrafficAnalyzeController(TrafficLayerAnalyzerService trafficLayerAnalyzerService, HttpServlet httpServlet) {
        this.trafficLayerAnalyzerService = trafficLayerAnalyzerService;
        this.httpServlet = httpServlet;
    }

    @GetMapping("/data-link")
    @ResponseStatus(HttpStatus.OK)
    public List<Ethernet> physicalLayer(@RequestPart("pcap") MultipartFile pcap) {
        String path = httpServlet.getServletContext().getRealPath(FolderConstants.PCAPS_FOLDER);
        if (pcap == null)
            throw new MultipartException("No pcap file provided");

        trafficLayerAnalyzerService.setPcap(pcap, path);


        return trafficLayerAnalyzerService.physical();
    }
}

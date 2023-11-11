package com.example.networkanalyzerspringboot.controllers;

import org.pcap4j.core.Pcaps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.pcap4j.core.PcapHandle;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        PcapHandle handle = null;
        try {
            handle = Pcaps.openOffline("pcaps/test.pcap");
            System.out.println(handle);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (handle != null && handle.isOpen()) {
                handle.close();
            }
        }

        return "test";
    }
}

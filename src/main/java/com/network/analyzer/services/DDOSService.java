package com.network.analyzer.services;

import com.network.analyzer.ddos.dtos.DdosIPv4DTO;

import java.util.LinkedHashMap;
import java.util.Set;

public interface DDOSService extends PacketService {


    LinkedHashMap<String, Set<DdosIPv4DTO>> getDDOSUDPPackets();
}

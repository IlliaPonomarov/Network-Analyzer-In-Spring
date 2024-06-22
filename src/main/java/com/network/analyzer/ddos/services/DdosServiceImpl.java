package com.network.analyzer.ddos.services;

import com.network.analyzer.ddos.dtos.DdosIPv4DTO;
import com.network.analyzer.services.DDOSService;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("ddosServiceImpl")
public class DdosServiceImpl implements DDOSService {

    private List<IpV4Packet> packets;

    @Override
    public LinkedHashMap<String, Set<DdosIPv4DTO>> getDDOSUDPPackets() {
        /*
         * Filter packets that are UDP
         */
        var udps = packets.stream()
                .filter(packet -> packet.get(UdpPacket.class) != null)
                .map(packet -> packet.get(IpV4Packet.class))
                .toList();

        /*
         * Group packets by source IP and source port
         */
        var groupedUDPsBySourceIpAndPort = udps.stream()
                .collect(Collectors.groupingBy(
                        ip -> ip.getHeader().getDstAddr().getHostAddress(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        var potentialDdos = new LinkedHashMap<String, Set<DdosIPv4DTO>>();

        /*
         * Group packets by source IP and source port
         * Then group packets by destination IP and destination port
         * Then count the number of requests to the destination
         */

        for(var entry : groupedUDPsBySourceIpAndPort.entrySet()) {
            var key = entry.getKey();
            var ipv4Packets = entry.getValue().stream().collect(Collectors.groupingBy(
                    ip -> new DdosIPv4DTO(ip.getHeader().getSrcAddr().getHostAddress(), ip.get(UdpPacket.class).getHeader().getSrcPort().valueAsInt()),
                    LinkedHashMap::new,
                    Collectors.toSet()
            ));

            var requestsToDst = ipv4Packets.keySet().stream()
                    .map(e -> {
                        var count = ipv4Packets.get(e).size();
                        e.setRequests(count);
                        return e;
                    })
                    .sorted(Comparator.comparing(DdosIPv4DTO::getRequests).reversed())
                    .toList();

            potentialDdos.put(key, new LinkedHashSet<>(requestsToDst));
        }

        return potentialDdos;
    }



    @Override
    public boolean collectPackets() {
        return false;
    }

    @Override
    public void setPackets(List<Packet> packets) {
        this.packets = packets.stream().map(packet -> packet.get(IpV4Packet.class)).toList();
    }
}

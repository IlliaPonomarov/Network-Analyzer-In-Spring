package com.network.analyzer.layers.physical.mappers;

import com.network.analyzer.layers.physical.models.Ethernet;
import com.network.analyzer.layers.physical.models.EthernetHeader;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;

import java.util.List;

public class EthernetMapper {

    public static List<Ethernet> toEthernetList(List<Packet> packets) {
        return packets.stream().map(packet -> {
            EthernetPacket ethernetPacket = packet.get(EthernetPacket.class);
            String sourceMacAddress = ethernetPacket.getHeader().getSrcAddr().toString();
            String destinationMacAddress = ethernetPacket.getHeader().getDstAddr().toString();
            String ethernetType = ethernetPacket.getHeader().getType().name();

            int length = ethernetPacket.getHeader().length();

            Ethernet ethernet = new Ethernet(
                    new EthernetHeader(
                            sourceMacAddress,
                            destinationMacAddress,
                            ethernetType,
                            length
                    ),
                    ethernetPacket.getPayload().getRawData()
            );

            return ethernet;
        }).toList();

    }
}

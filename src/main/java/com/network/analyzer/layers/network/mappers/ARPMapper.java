package com.network.analyzer.layers.network.mappers;

import com.network.analyzer.layers.network.models.ARP;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.Packet;

public class ARPMapper {
    public static ARP toARP(Packet packet) {
        ArpPacket arpPacket = packet.get(ArpPacket.class);

        String hardwareType = arpPacket.getHeader().getHardwareType().name();
        String protocolType = arpPacket.getHeader().getProtocolType().name();
        int hardwareSize = arpPacket.getHeader().getHardwareAddrLengthAsInt();
        int protocolSize = arpPacket.getHeader().getProtocolAddrLengthAsInt();
        String opcode = arpPacket.getHeader().getOperation().name();
        String senderMacAddress = arpPacket.getHeader().getSrcHardwareAddr().toString();
        String senderIPAddress = arpPacket.getHeader().getSrcProtocolAddr().toString();
        senderIPAddress = senderIPAddress.replace("/", "");

        String targetMacAddress = arpPacket.getHeader().getDstHardwareAddr().toString();
        String targetIPAddress = arpPacket.getHeader().getDstProtocolAddr().toString();
        targetIPAddress = targetIPAddress.replace("/", "");

        return new ARP(hardwareType, protocolType, hardwareSize, protocolSize, opcode, senderMacAddress, senderIPAddress, targetMacAddress, targetIPAddress);
    }
}

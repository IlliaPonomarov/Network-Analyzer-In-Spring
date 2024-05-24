package com.network.analyzer.layers;


import com.network.analyzer.layers.network.mappers.ARPMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.ArpHardwareType;
import org.pcap4j.packet.namednumber.ArpOperation;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.util.MacAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/***
 * @author Illia Ponomarov
 * This class is responsible for testing ARPMapper class
 * @see ARPMapper
 */
@ExtendWith(MockitoExtension.class)
public class ARPMapperTests {


    @Test
    public void testToARP() throws UnknownHostException {

        var arpPacket = mock(ArpPacket.class);
        var arpHeader = mock(ArpPacket.ArpHeader.class);

        // Given
        when(arpPacket.getHeader()).thenReturn(arpHeader);
        when(arpHeader.getHardwareType()).thenReturn(ArpHardwareType.ETHERNET);
        when(arpHeader.getProtocolType()).thenReturn(EtherType.IPV4);
        when(arpHeader.getHardwareAddrLengthAsInt()).thenReturn(6);
        when(arpHeader.getProtocolAddrLengthAsInt()).thenReturn(4);
        when(arpHeader.getOperation()).thenReturn(ArpOperation.REQUEST);
        when(arpHeader.getSrcHardwareAddr()).thenReturn(MacAddress.getByName("00:00:00:00:00:00"));
        when(arpHeader.getSrcProtocolAddr()).thenReturn(InetAddress.getLocalHost());
        when(arpHeader.getDstHardwareAddr()).thenReturn(MacAddress.getByName("00:00:00:00:00:00"));
        when(arpHeader.getDstProtocolAddr()).thenReturn(InetAddress.getLocalHost());

        var packet = mock(Packet.class);
        when(packet.get(ArpPacket.class)).thenReturn(arpPacket);

        // When
        var arp = ARPMapper.toARP(packet);

        // Then
        assertEquals("Ethernet (10Mb)", arp.getHardwareType());
        assertEquals("IPv4", arp.getProtocolType());
        assertEquals(6, arp.getHardwareSize());
        assertEquals(4, arp.getProtocolSize());
        assertEquals("REQUEST", arp.getOpcode());
        assertEquals("00:00:00:00:00:00", arp.getSenderMacAddress());
        assertEquals("DESKTOP-3GCU0S4192.168.56.1", arp.getSenderIPAddress());
        assertEquals("00:00:00:00:00:00", arp.getTargetMacAddress());
        assertEquals("DESKTOP-3GCU0S4192.168.56.1", arp.getTargetIPAddress());

    }


}

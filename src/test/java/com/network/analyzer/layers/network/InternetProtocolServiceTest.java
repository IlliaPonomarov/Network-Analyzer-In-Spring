package com.network.analyzer.layers.network;

import com.network.analyzer.layers.network.mappers.IPv4Mapper;
import com.network.analyzer.layers.network.mappers.IPv6Mapper;
import com.network.analyzer.layers.network.models.InternetProtocolV4;
import com.network.analyzer.layers.network.models.InternetProtocolV6;
import com.network.analyzer.layers.network.services.InternetProtocolServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InternetProtocolServiceTest {




    @Mock
    private List<Packet> packets;

    @Mock
    private Packet ipv4Packet;

    @Mock
    private Packet icmpV4Packet;

    @Mock
    private Packet icmpV6Packet;

    @Mock
    private Packet arpPacket;

    @Mock
    private IpV4Packet ipV4Packet;

    @InjectMocks
    private static InternetProtocolServiceImpl internetProtocolService ;


    @BeforeEach
     void setUp() {

        int size = 10;

        // generate 10 randmo ipv4 and ipv6 packets
        IntStream.range(0, size).forEach(i -> {
                    packets.add(mock(IpV4Packet.class));
                });

        IntStream.range(0, size).forEach(i -> {
                      packets.add(mock(IpV6Packet.class));
                 });

       internetProtocolService.setPackets(packets);
    }


    @Test
    void collectPacketsTest() {
        var packet = mock(Packet.class);

        assertFalse(internetProtocolService.collectPackets());
    }

    @ParameterizedTest
    @CsvSource({
            "IPv4, 192.168.1.0",
            "IPv6, 2001:db8::ff00:42:8329"
    })
    public void findInternetProtocolsByIPVersionAndIPTest(String version, String ip) {
        var packet = mock(Packet.class);
        var ipV4Packet = mock(IpV4Packet.class);
        var ipV6Packet = mock(IpV6Packet.class);



    }

}

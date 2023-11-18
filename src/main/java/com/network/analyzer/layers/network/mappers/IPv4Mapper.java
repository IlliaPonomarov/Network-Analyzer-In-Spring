package com.network.analyzer.layers.network.mappers;

import com.network.analyzer.layers.network.models.InternetProtocolV4;
import org.pcap4j.packet.GtpV1Packet;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;

import java.util.List;

public class IPv4Mapper {

    public static InternetProtocolV4 toInternetProtocol(Packet packet) {
            IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
            String sourceIP = ipV4Packet.getHeader().getSrcAddr().toString();
            String destinationIP = ipV4Packet.getHeader().getDstAddr().toString();
            String version = ipV4Packet.getHeader().getVersion().name();
            String protocol = ipV4Packet.getHeader().getProtocol().name();
            int length = ipV4Packet.getHeader().length();
            int identification = ipV4Packet.getHeader().getIdentificationAsInt();
            int timeToLive = ipV4Packet.getHeader().getTtlAsInt();
            int headerChecksum = ipV4Packet.getHeader().getHeaderChecksum();
            int totalLength = ipV4Packet.getHeader().getTotalLengthAsInt();
            int fragmentOffset = ipV4Packet.getHeader().getFragmentOffset();
            int flags = ipV4Packet.getHeader().getReservedFlag() ? 1 : 0;
            int headerLength = ipV4Packet.getHeader().getIhlAsInt();
            int protocolType = ipV4Packet.getHeader().getProtocol().value();
            byte[] payload = ipV4Packet.getPayload().getRawData();

            return new InternetProtocolV4(
                    sourceIP,
                    destinationIP,
                    version,
                    length,
                    protocol,
                    identification,
                    timeToLive,
                    headerChecksum,
                    totalLength,
                    fragmentOffset,
                    flags,
                    headerLength,
                    protocolType,
                    payload
    );

    }
}

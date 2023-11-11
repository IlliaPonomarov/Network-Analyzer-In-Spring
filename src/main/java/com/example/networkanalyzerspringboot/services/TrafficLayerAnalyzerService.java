package com.example.networkanalyzerspringboot.services;

import com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions.PacketListIsEmptyException;
import com.example.networkanalyzerspringboot.models.Ethernet;
import com.example.networkanalyzerspringboot.models.EthernetHeader;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.EtherType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope("prototype")
public class TrafficLayerAnalyzerService {

    private  MultipartFile pcap;
    private List<Packet> packets = new ArrayList<>();
    private List<Ethernet> physical = new ArrayList<>();


    public List<Ethernet> physical() {



        if (packets.isEmpty())
            throw new PacketListIsEmptyException("Packet list is empty");

        if (!physical.isEmpty())
            return physical;

        for (Packet packet : packets) {
            EthernetPacket ethernetPacket = packet.get(EthernetPacket.class);
            String sourceMacAddress = ethernetPacket.getHeader().getSrcAddr().toString();
            String destinationMacAddress = ethernetPacket.getHeader().getDstAddr().toString();
            EtherType ethernetType = ethernetPacket.getHeader().getType();
            Ethernet ethernet = new Ethernet(
                    new EthernetHeader(
                            sourceMacAddress,
                            destinationMacAddress,
                            ethernetType.valueAsString()
                    ),
                    ethernetPacket.getPayload().getRawData()
            );

            physical.add(ethernet);

        }

        return physical;
    }

    public void setPackets(List<Packet> packets) {
        this.packets = packets;
    }

    public void setPcap(MultipartFile pcap , String requestPath) {
        String path = String.format("%s/%s-%s", requestPath , UUID.randomUUID().toString(), pcap.getOriginalFilename());
        try {
            File file = new File(path);

            if (!new File(requestPath).exists())
                new File(requestPath).mkdirs();

            if (!file.exists())
                file.createNewFile();

            pcap.transferTo(file);
        } catch (Exception e) {

            e.printStackTrace();
         }

        try(PcapHandle handle = Pcaps.openOffline(path)) {

            for (Packet packet = handle.getNextPacketEx(); packet != null ; packet = handle.getNextPacketEx())
                packets.add(packet);

            if (handle.isOpen())
                handle.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new File(path).delete();
        }

        this.pcap = pcap;
    }
}

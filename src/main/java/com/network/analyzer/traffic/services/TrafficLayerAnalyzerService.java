package com.network.analyzer.traffic.services;

import com.network.analyzer.storage.exceptions.PacketListIsEmptyException;
import com.network.analyzer.layers.physical.models.Ethernet;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
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

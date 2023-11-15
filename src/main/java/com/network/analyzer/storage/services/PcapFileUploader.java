package com.network.analyzer.storage.services;

import jakarta.servlet.http.HttpServlet;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PcapFileUploader {

    private String path;
    private MultipartFile pcap;
    private String requestPath;

    private List<Packet> packets = new ArrayList<>();

    public PcapFileUploader(MultipartFile pcap, String path, String requestPath) {
        this.pcap = pcap;
        this.path = path;
        this.requestPath = requestPath;
    }

    public List<Packet> getPacketsByPcapUUID(String pcapUUID) {
        String path = String.format("%s/%s", requestPath, pcapUUID);
        try (PcapHandle handle = Pcaps.openOffline(path)) {

            for (Packet packet = handle.getNextPacketEx(); packet != null; packet = handle.getNextPacketEx())
                packets.add(packet);

            if (handle.isOpen())
                handle.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return packets;
    }

    @Deprecated
    public List<Packet> getPackets() {
        String path = String.format("%s/%s.pcap", requestPath, UUID.randomUUID().toString());
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

        try (PcapHandle handle = Pcaps.openOffline(path)) {

            for (Packet packet = handle.getNextPacketEx(); packet != null; packet = handle.getNextPacketEx())
                packets.add(packet);

            if (handle.isOpen())
                handle.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new File(path).delete();
        }

        return packets;
    }

    public List<String> getListOfFiles() {
        File folder = new File(requestPath);
        File[] listOfFiles = folder.listFiles();
        List<String> files = new ArrayList<>();

        for (File file : listOfFiles)
            files.add(file.getName());

        return files;
    }
}

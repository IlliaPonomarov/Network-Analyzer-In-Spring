package com.network.analyzer.layers.transport.models;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class UDP {
    private final UUID id;
    private String sourcePort;
    private String destinationPort;
    private int length;
    private int checksum;
    private byte[] data;

    public UDP(String sourcePort, String destinationPort, int length, int checksum, byte[] data) {
        this.id = UUID.randomUUID();
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.length = length;
        this.checksum = checksum;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public int getLength() {
        return length;
    }


    public int getChecksum() {
        return checksum;
    }



    public byte[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UDP udp = (UDP) o;
        return length == udp.length && checksum == udp.checksum && Objects.equals(id, udp.id) && Objects.equals(sourcePort, udp.sourcePort) && Objects.equals(destinationPort, udp.destinationPort) && Arrays.equals(data, udp.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourcePort, destinationPort, length, checksum, Arrays.hashCode(data));
    }

    @Override
    public String toString() {
        return "UDP{" +
                "id=" + id +
                ", sourcePort='" + sourcePort + '\'' +
                ", destinationPort='" + destinationPort + '\'' +
                ", length=" + length +
                ", checksum=" + checksum +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

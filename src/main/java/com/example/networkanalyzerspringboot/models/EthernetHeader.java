package com.example.networkanalyzerspringboot.models;

import org.pcap4j.packet.namednumber.EtherType;

import java.util.Objects;
import java.util.UUID;

public class EthernetHeader {

    private final UUID id;
    private String sourceMacAddress;
    private String destinationMacAddress;
    private String ethernetType;

    public EthernetHeader(String sourceMacAddress, String destinationMacAddress, String ethernetType) {
        this.id = UUID.randomUUID();
        this.sourceMacAddress = sourceMacAddress;
        this.destinationMacAddress = destinationMacAddress;
        this.ethernetType = ethernetType;
    }

    public UUID getId() {
        return id;
    }

    public String getSourceMacAddress() {
        return sourceMacAddress;
    }

    public void setSourceMacAddress(String sourceMacAddress) {
        this.sourceMacAddress = sourceMacAddress;
    }

    public String getDestinationMacAddress() {
        return destinationMacAddress;
    }

    public void setDestinationMacAddress(String destinationMacAddress) {
        this.destinationMacAddress = destinationMacAddress;
    }

    public String getEthernetType() {
        return ethernetType;
    }

    public void setEthernetType(String ethernetType) {
        this.ethernetType = ethernetType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EthernetHeader that = (EthernetHeader) o;
        return Objects.equals(id, that.id) && Objects.equals(sourceMacAddress, that.sourceMacAddress) && Objects.equals(destinationMacAddress, that.destinationMacAddress) && Objects.equals(ethernetType, that.ethernetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceMacAddress, destinationMacAddress, ethernetType);
    }

    @Override
    public String toString() {
        return "EthernetHeader{" +
                "id=" + id +
                ", sourceMacAddress='" + sourceMacAddress + '\'' +
                ", destinationMacAddress='" + destinationMacAddress + '\'' +
                ", ethernetType=" + ethernetType +
                '}';
    }
}

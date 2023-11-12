package com.example.networkanalyzerspringboot.models;

import java.util.Objects;
import java.util.UUID;

public class EthernetHeader {

    private final UUID id;
    private String sourceMacAddress;
    private String destinationMacAddress;
    private String ethernetType;
    private int length;

    public EthernetHeader(String sourceMacAddress, String destinationMacAddress, String ethernetType, int length) {
        this.id = UUID.randomUUID();
        this.sourceMacAddress = sourceMacAddress;
        this.destinationMacAddress = destinationMacAddress;
        this.ethernetType = ethernetType;
        this.length = length;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
        return length == that.length && Objects.equals(id, that.id) && Objects.equals(sourceMacAddress, that.sourceMacAddress) && Objects.equals(destinationMacAddress, that.destinationMacAddress) && Objects.equals(ethernetType, that.ethernetType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceMacAddress, destinationMacAddress, ethernetType, length);
    }

    @Override
    public String toString() {
        return "EthernetHeader{" +
                "id=" + id +
                ", sourceMacAddress='" + sourceMacAddress + '\'' +
                ", destinationMacAddress='" + destinationMacAddress + '\'' +
                ", ethernetType='" + ethernetType + '\'' +
                ", length=" + length +
                '}';
    }
}

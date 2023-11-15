package com.network.analyzer.layers.network.models;

import com.network.analyzer.layers.transport.models.TCP;

import java.util.Objects;
import java.util.UUID;

public class InternetProtocol {

    private final UUID id;
    private String sourceIpAddress;
    private String destinationIpAddress;
    private com.network.analyzer.layers.transport.models.TCP TCP;
    private int length;
    private int timeToLive;
    private int headerChecksum;
    private int identification;

    public InternetProtocol(String sourceIpAddress, String destinationIpAddress, TCP TCP, int length, int timeToLive, int headerChecksum, int identification) {
        this.id = UUID.randomUUID();
        this.sourceIpAddress = sourceIpAddress;
        this.destinationIpAddress = destinationIpAddress;
        this.TCP = TCP;
        this.length = length;
        this.timeToLive = timeToLive;
        this.headerChecksum = headerChecksum;
        this.identification = identification;
    }

    public UUID getId() {
        return id;
    }

    public String getSourceIpAddress() {
        return sourceIpAddress;
    }

    public void setSourceIpAddress(String sourceIpAddress) {
        this.sourceIpAddress = sourceIpAddress;
    }

    public String getDestinationIpAddress() {
        return destinationIpAddress;
    }

    public void setDestinationIpAddress(String destinationIpAddress) {
        this.destinationIpAddress = destinationIpAddress;
    }


    public TCP getProtocol() {
        return TCP;
    }

    public void setProtocol(TCP TCP) {
        this.TCP = TCP;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getHeaderChecksum() {
        return headerChecksum;
    }

    public void setHeaderChecksum(int headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternetProtocol that = (InternetProtocol) o;
        return length == that.length && timeToLive == that.timeToLive && headerChecksum == that.headerChecksum && identification == that.identification && Objects.equals(id, that.id) && Objects.equals(sourceIpAddress, that.sourceIpAddress) && Objects.equals(destinationIpAddress, that.destinationIpAddress) && Objects.equals(TCP, that.TCP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceIpAddress, destinationIpAddress, TCP, length, timeToLive, headerChecksum, identification);
    }

    @Override
    public String toString() {
        return "InternetProtocol{" +
                "id=" + id +
                ", sourceIpAddress='" + sourceIpAddress + '\'' +
                ", destinationIpAddress='" + destinationIpAddress + '\'' +
                ", protocol=" + TCP +
                ", length=" + length +
                ", timeToLive=" + timeToLive +
                ", headerChecksum=" + headerChecksum +
                ", identification=" + identification +
                '}';
    }
}

package com.network.analyzer.layers.network.models;

import com.network.analyzer.layers.transport.models.TCP;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class InternetProtocolV4 {

    private final UUID id;
    private String sourceIpAddress;
    private String destinationIpAddress;
    private int length;
    private int timeToLive;
    private int headerChecksum;
    private int identification;
    private String version;
    private int totalLength;
    private int fragmentOffset;
    private int flags;
    private int headerLength;
    private int protocolType;
    private byte[] payload;

    public InternetProtocolV4(String sourceIP, String destinationIP, String version, int length, String protocol, int identification, int timeToLive, int headerChecksum, int totalLength, int fragmentOffset, int flags, int headerLength, int protocolType, byte[] payload) {
        this.id = UUID.randomUUID();
        this.sourceIpAddress = sourceIP;
        this.destinationIpAddress = destinationIP;
        this.length = length;
        this.timeToLive = timeToLive;
        this.headerChecksum = headerChecksum;
        this.identification = identification;
        this.version = version;
        this.totalLength = totalLength;
        this.fragmentOffset = fragmentOffset;
        this.flags = flags;
        this.headerLength = headerLength;
        this.protocolType = protocolType;
        this.payload = payload;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public int getFragmentOffset() {
        return fragmentOffset;
    }

    public void setFragmentOffset(int fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternetProtocolV4 that = (InternetProtocolV4) o;
        return length == that.length
                && timeToLive == that.timeToLive
                && headerChecksum == that.headerChecksum
                && identification == that.identification
                && totalLength == that.totalLength
                && fragmentOffset == that.fragmentOffset
                && flags == that.flags
                && headerLength == that.headerLength
                && protocolType == that.protocolType
                && Objects.equals(id, that.id)
                && Objects.equals(sourceIpAddress, that.sourceIpAddress)
                && Objects.equals(destinationIpAddress, that.destinationIpAddress)
                && Objects.equals(version, that.version)
                && Arrays.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceIpAddress, destinationIpAddress, length, timeToLive, headerChecksum, identification, version, totalLength, fragmentOffset, flags, headerLength, protocolType, payload);
    }

    @Override
    public String toString() {
        return "InternetProtocolV4{" +
                "id=" + id +
                ", sourceIpAddress='" + sourceIpAddress + '\'' +
                ", destinationIpAddress='" + destinationIpAddress + '\'' +
                ", length=" + length +
                ", timeToLive=" + timeToLive +
                ", headerChecksum=" + headerChecksum +
                ", identification=" + identification +
                ", version='" + version + '\'' +
                ", totalLength=" + totalLength +
                ", fragmentOffset=" + fragmentOffset +
                ", flags=" + flags +
                ", headerLength=" + headerLength +
                ", protocolType=" + protocolType +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }




}

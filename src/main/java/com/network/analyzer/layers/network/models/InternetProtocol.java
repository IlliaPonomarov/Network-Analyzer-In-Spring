package com.network.analyzer.layers.network.models;

import java.util.Objects;

public class InternetProtocol {
    private String sourceIpAddress;
    private String destinationIpAddress;
    private int length;
    private String version;

    public InternetProtocol(String sourceIpAddress, String destinationIpAddress, int length, String version) {
        this.sourceIpAddress = sourceIpAddress;
        this.destinationIpAddress = destinationIpAddress;
        this.length = length;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternetProtocol that = (InternetProtocol) o;
        return length == that.length && Objects.equals(sourceIpAddress, that.sourceIpAddress) && Objects.equals(destinationIpAddress, that.destinationIpAddress) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceIpAddress, destinationIpAddress, length, version);
    }

    @Override
    public String toString() {
        return "InternetProtocol{" +
                "sourceIpAddress='" + sourceIpAddress + '\'' +
                ", destinationIpAddress='" + destinationIpAddress + '\'' +
                ", length=" + length +
                ", version='" + version + '\'' +
                '}';
    }
}

package com.network.analyzer.layers.network.models;

import java.util.Objects;
import java.util.UUID;

public class InternetProtocolV6 extends InternetProtocol{

    private final UUID id;
    private int trafficClass;
    private int flowLabel;
    private int payloadLength;
    private int nextHeader;
    private int hopLimit;

    public InternetProtocolV6() {
        this("", "", 0, "", 0, 0, 0, 0, 0);
    }

    public InternetProtocolV6(String sourceIpAddress, String destinationIpAddress, int length, String version, int trafficClass, int flowLabel, int payloadLength, int nextHeader, int hopLimit) {
        super(sourceIpAddress, destinationIpAddress, length, version);
        this.id = UUID.randomUUID();
        this.trafficClass = trafficClass;
        this.flowLabel = flowLabel;
        this.payloadLength = payloadLength;
        this.nextHeader = nextHeader;
        this.hopLimit = hopLimit;
    }

    public UUID getId() {
        return id;
    }

    public int getTrafficClass() {
        return trafficClass;
    }

    public void setTrafficClass(int trafficClass) {
        this.trafficClass = trafficClass;
    }

    public int getFlowLabel() {
        return flowLabel;
    }

    public void setFlowLabel(int flowLabel) {
        this.flowLabel = flowLabel;
    }

    public int getPayloadLength() {
        return payloadLength;
    }

    public void setPayloadLength(int payloadLength) {
        this.payloadLength = payloadLength;
    }

    public int getNextHeader() {
        return nextHeader;
    }

    public void setNextHeader(int nextHeader) {
        this.nextHeader = nextHeader;
    }

    public int getHopLimit() {
        return hopLimit;
    }

    public void setHopLimit(int hopLimit) {
        this.hopLimit = hopLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InternetProtocolV6 that = (InternetProtocolV6) o;
        return payloadLength == that.payloadLength && nextHeader == that.nextHeader && hopLimit == that.hopLimit && Objects.equals(id, that.id) && Objects.equals(trafficClass, that.trafficClass) && Objects.equals(flowLabel, that.flowLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, trafficClass, flowLabel, payloadLength, nextHeader, hopLimit);
    }

    @Override
    public String toString() {
        return "InternetProtocolV6{" +
                "id=" + id +
                ", trafficClass='" + trafficClass + '\'' +
                ", flowLabel='" + flowLabel + '\'' +
                ", payloadLength=" + payloadLength +
                ", nextHeader=" + nextHeader +
                ", hopLimit=" + hopLimit +
                '}';
    }
}

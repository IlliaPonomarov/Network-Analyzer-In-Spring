package com.network.analyzer.layers.transport.models;

import com.network.analyzer.utility.ProtocolType;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class TCP {
    private final UUID id;
    private String sourcePort;
    private String destinationPort;
    private int checksum;
    private int windowSize;
    private int sequenceNumber;
    private int acknowledgementNumber;
    private int urgentPointer;
    private int options;
    private int flags;
    private int headerLength;

    private byte[] data;


    public TCP(String sourcePort, String destinationPort, int checksum, int windowSize, int sequenceNumber, int acknowledgementNumber, int urgentPointer, int options, int flags, int headerLength, byte[] data) {
        this.id = UUID.randomUUID();
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.checksum = checksum;
        this.windowSize = windowSize;
        this.sequenceNumber = sequenceNumber;
        this.acknowledgementNumber = acknowledgementNumber;
        this.urgentPointer = urgentPointer;
        this.options = options;
        this.flags = flags;
        this.headerLength = headerLength;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public int getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(int windowSize) {
        this.windowSize = windowSize;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getAcknowledgementNumber() {
        return acknowledgementNumber;
    }

    public void setAcknowledgementNumber(int acknowledgementNumber) {
        this.acknowledgementNumber = acknowledgementNumber;
    }

    public int getUrgentPointer() {
        return urgentPointer;
    }

    public void setUrgentPointer(int urgentPointer) {
        this.urgentPointer = urgentPointer;
    }

    public int getOptions() {
        return options;
    }

    public void setOptions(int options) {
        this.options = options;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCP tcp = (TCP) o;
        return checksum == tcp.checksum && windowSize == tcp.windowSize && sequenceNumber == tcp.sequenceNumber && acknowledgementNumber == tcp.acknowledgementNumber && urgentPointer == tcp.urgentPointer && options == tcp.options && flags == tcp.flags && headerLength == tcp.headerLength && Objects.equals(id, tcp.id) && Objects.equals(sourcePort, tcp.sourcePort) && Objects.equals(destinationPort, tcp.destinationPort) && Arrays.equals(data, tcp.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, sourcePort, destinationPort, checksum, windowSize, sequenceNumber, acknowledgementNumber, urgentPointer, options, flags, headerLength);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "TCP{" +
                "id=" + id +
                ", sourcePort='" + sourcePort + '\'' +
                ", destinationPort='" + destinationPort + '\'' +
                ", checksum=" + checksum +
                ", windowSize=" + windowSize +
                ", sequenceNumber=" + sequenceNumber +
                ", acknowledgementNumber=" + acknowledgementNumber +
                ", urgentPointer=" + urgentPointer +
                ", options=" + options +
                ", flags=" + flags +
                ", headerLength=" + headerLength +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

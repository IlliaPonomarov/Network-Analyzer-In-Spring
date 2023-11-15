package com.network.analyzer.layers.transport.models;

import com.network.analyzer.utility.ProtocolType;

import java.util.UUID;

public class TCP {
    private final UUID id;
    private String sourcePort;
    private String destinationPort;
    private ProtocolType protocolType;
    private int timeToLive;
    private int checksum;
    private int windowSize;
    private int sequenceNumber;
    private int acknowledgementNumber;
    private int urgentPointer;
    private int options;
    private int flags;
    private int headerLength;

    private byte[] data;


    public TCP(String sourcePort, String destinationPort, ProtocolType protocolType, int timeToLive, int checksum, int windowSize, int sequenceNumber, int acknowledgementNumber, int urgentPointer, int options, int flags, int headerLength, byte[] data) {
        this.id = UUID.randomUUID();
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.protocolType = protocolType;
        this.timeToLive = timeToLive;
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
}

package com.network.analyzer.layers.network.models;

import java.util.Objects;
import java.util.UUID;

public class ARP {

    private final UUID id;
    private final String hardwareType;
    private final String protocolType;
    private final int hardwareSize;
    private final int protocolSize;
    private final String opcode;
    private final String senderMacAddress;
    private final String senderIPAddress;
    private final String targetMacAddress;
    private final String targetIPAddress;

    public ARP(String hardwareType, String protocolType, int hardwareSize, int protocolSize, String opcode, String senderMacAddress, String senderIPAddress, String targetMacAddress, String targetIPAddress) {
        this.id = UUID.randomUUID();
        this.hardwareType = hardwareType;
        this.protocolType = protocolType;
        this.hardwareSize = hardwareSize;
        this.protocolSize = protocolSize;
        this.opcode = opcode;
        this.senderMacAddress = senderMacAddress;
        this.senderIPAddress = senderIPAddress;
        this.targetMacAddress = targetMacAddress;
        this.targetIPAddress = targetIPAddress;
    }

    public UUID getId() {
        return id;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public int getHardwareSize() {
        return hardwareSize;
    }

    public int getProtocolSize() {
        return protocolSize;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getSenderMacAddress() {
        return senderMacAddress;
    }

    public String getSenderIPAddress() {
        return senderIPAddress;
    }

    public String getTargetMacAddress() {
        return targetMacAddress;
    }

    public String getTargetIPAddress() {
        return targetIPAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ARP arp = (ARP) o;
        return Objects.equals(id, arp.id) && Objects.equals(hardwareType, arp.hardwareType) && Objects.equals(protocolType, arp.protocolType) && Objects.equals(hardwareSize, arp.hardwareSize) && Objects.equals(protocolSize, arp.protocolSize) && Objects.equals(opcode, arp.opcode) && Objects.equals(senderMacAddress, arp.senderMacAddress) && Objects.equals(senderIPAddress, arp.senderIPAddress) && Objects.equals(targetMacAddress, arp.targetMacAddress) && Objects.equals(targetIPAddress, arp.targetIPAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hardwareType, protocolType, hardwareSize, protocolSize, opcode, senderMacAddress, senderIPAddress, targetMacAddress, targetIPAddress);
    }


    @Override
    public String toString() {
        return "ARP{" +
                "id=" + id +
                ", hardwareType='" + hardwareType + '\'' +
                ", protocolType='" + protocolType + '\'' +
                ", hardwareSize='" + hardwareSize + '\'' +
                ", protocolSize='" + protocolSize + '\'' +
                ", opcode='" + opcode + '\'' +
                ", senderMacAddress='" + senderMacAddress + '\'' +
                ", senderIPAddress='" + senderIPAddress + '\'' +
                ", targetMacAddress='" + targetMacAddress + '\'' +
                ", targetIPAddress='" + targetIPAddress + '\'' +
                '}';
    }
}

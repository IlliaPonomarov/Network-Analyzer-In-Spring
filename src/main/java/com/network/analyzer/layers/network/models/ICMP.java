package com.network.analyzer.layers.network.models;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class ICMP {

    private final UUID id;
    private String type;
    private String code;
    private int checksum;
    private byte[] payload;

    public ICMP(String type, String code, int checksum, byte[] payload) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.code = code;
        this.checksum = checksum;
        this.payload = payload;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum(int checksum) {
        this.checksum = checksum;
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
        ICMP icmp = (ICMP) o;
        return checksum == icmp.checksum && Objects.equals(id, icmp.id) && Objects.equals(type, icmp.type) && Objects.equals(code, icmp.code) && Arrays.equals(payload, icmp.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, code, checksum, Arrays.hashCode(payload));
    }

    @Override
    public String toString() {
        return "ICMP{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", checksum=" + checksum +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}

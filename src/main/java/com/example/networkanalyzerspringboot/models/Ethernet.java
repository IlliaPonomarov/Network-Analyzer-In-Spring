package com.example.networkanalyzerspringboot.models;

import com.example.networkanalyzerspringboot.utility.EthernetType;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Ethernet {

    private final UUID id;
    private EthernetHeader ethernetHeader;
    private byte[] payload;

    public Ethernet(EthernetHeader ethernetHeader, byte[] payload) {
        this.id = UUID.randomUUID();
        this.ethernetHeader = ethernetHeader;
        this.payload = payload;

    }

    public UUID getId() {
        return id;
    }

    public EthernetHeader getEthernetHeader() {
        return ethernetHeader;
    }

    public void setEthernetHeader(EthernetHeader ethernetHeader) {
        this.ethernetHeader = ethernetHeader;
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
        Ethernet ethernet = (Ethernet) o;
        return Objects.equals(id, ethernet.id) && Objects.equals(ethernetHeader, ethernet.ethernetHeader) && Arrays.equals(payload, ethernet.payload);
    }

    @Override
    public int hashCode() {
       return Objects.hash(id, ethernetHeader, payload);
    }

    @Override
    public String toString() {
        return "Ethernet{" +
                "id=" + id +
                ", ethernetHeader=" + ethernetHeader +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}

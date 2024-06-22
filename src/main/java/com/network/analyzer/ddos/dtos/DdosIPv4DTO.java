package com.network.analyzer.ddos.dtos;

import java.util.Objects;

public class DdosIPv4DTO{
    private String srcAddr;
    private int srcPort;
    private long requests;

    public DdosIPv4DTO(String srcAddr, int srcPort){
        this.srcAddr = srcAddr;
        this.srcPort = srcPort;

    }

    public DdosIPv4DTO(String srcAddr, int srcPort, long requests){
        this.srcAddr = srcAddr;
        this.srcPort = srcPort;
        this.requests = requests;
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public int getSrcPort() {
        return srcPort;
    }

    public long getRequests() {
        return requests;
    }

public void setRequests(long requests){
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DdosIPv4DTO that = (DdosIPv4DTO) o;
        return srcPort == that.srcPort && requests == that.requests && Objects.equals(srcAddr, that.srcAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcAddr, srcPort, requests);
    }

    @Override
    public String toString() {
        return "DdosIPv4DTO{" +
                "srcAddr='" + srcAddr + '\'' +
                ", srcPort=" + srcPort +
                ", requests=" + requests +
                '}';
    }
}

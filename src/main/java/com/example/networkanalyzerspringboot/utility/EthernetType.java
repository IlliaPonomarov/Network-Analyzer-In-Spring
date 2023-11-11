package com.example.networkanalyzerspringboot.utility;

public interface EthernetType {

    short ETHERNET_IPv4 = 0x80;
    short ETHERNET_IPv6 = 0x86;
    short ETHERNET_ARP = 0x08;
    short ETHERNET_RARP = 0x21;
    short ETHERNET_APPLETALK = 0x80;
    int NOVELL_IPX = 0x8137;
    int NOVELL_802_3 = 0x8138;
    int NOVELL_ETHERNET = 0x8139;
    int NOVELL_FDDI = 0x813A;
    int NOVELL_TR_802_2 = 0x813B;
    int NOVELL_TR_SNAP = 0x813C;
    int IEEE_802_1Q_VLAN_TAGGED_FRAMES = 0x8100;
    int IEEE_802_1Q_PROVIDER_BRIDGING = 0x88A8;
    int IEEE_802_1Q_LAN_TAGGED_FRAMES = 0x88A8;
    int IEEE_802_1Q_RESERVED_1 = 0x88A9;
    int IEEE_802_1Q_RESERVED_2 = 0x88AA;

}
package com.example.networkanalyzerspringboot.services;


import com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions.StorageException;
import com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions.StorageFileNotFoundException;
import org.pcap4j.packet.Packet;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init() throws StorageException;

    void store(MultipartFile file) throws StorageException;

    Stream<Path> loadAll() throws StorageException;

    Path load(String filename);

    List<Packet> loadPackets(String filename) throws StorageFileNotFoundException;

    Resource loadAsResource(String filename) throws StorageFileNotFoundException;

    void deleteAll();

}
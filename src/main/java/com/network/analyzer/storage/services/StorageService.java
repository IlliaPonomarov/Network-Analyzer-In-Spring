package com.network.analyzer.storage.services;


import com.network.analyzer.storage.exceptions.StorageException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import org.pcap4j.packet.Packet;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface StorageService {

    void init() throws StorageException;

    String store(MultipartFile file) throws StorageException;

    Stream<Path> loadAll() throws StorageException;

    Path load(String filename);

    List<Packet> loadPackets(String filename) throws StorageFileNotFoundException;

    Resource loadAsResource(String filename) throws StorageFileNotFoundException;

    void deleteAll();

}
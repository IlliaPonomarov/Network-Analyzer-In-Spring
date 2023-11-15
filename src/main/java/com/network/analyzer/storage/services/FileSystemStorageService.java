package com.network.analyzer.storage.services;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.network.analyzer.config.StorageConfig;
import com.network.analyzer.storage.exceptions.StorageException;
import com.network.analyzer.storage.exceptions.StorageFileNotFoundException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageConfig properties) throws StorageException {

        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) throws StorageException {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(UUID.randomUUID().toString() + ".pcap"))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() throws StorageException {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageFileNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException | StorageFileNotFoundException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public List<Packet> loadPackets(String filename) throws StorageFileNotFoundException {
        List<Packet> packets = new ArrayList<>();

        try {
            Path file = load(filename +".pcap");
            Resource resource = new UrlResource(file.toUri());


            if (!resource.exists() || !resource.isReadable())
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            String path = String.format("%s",file.toFile());
            try (PcapHandle handle = Pcaps.openOffline(path)) {

                for (Packet packet = handle.getNextPacketEx(); packet != null; packet = handle.getNextPacketEx())
                    packets.add(packet);

                if (handle.isOpen())
                    handle.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException | StorageFileNotFoundException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return packets;
    }



    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() throws StorageException {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
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
import com.network.analyzer.services.logger.LoggerService;
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

/***
 * Service annotation is used in your service bucketName and annotates classes that perform service tasks, often you don't use it but in many case you use it in your service classes.
 * @see Service
 * This class is responsible for storing files in the file system
 * @see StorageService
 * @see StorageException
 * @see StorageFileNotFoundException
 *
 * @author Illia Ponomarov
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    /***
     * Constructor for FileSystemStorageService
     * @param properties - properties for storage
     * @throws StorageException - if file upload location is invalid
     * @see StorageException
     */
    @Autowired
    public FileSystemStorageService(StorageConfig properties) throws StorageException{

        if (properties.getLocation().trim().isEmpty()) {
            throw new StorageException("File upload location defined at StorageConfig is invalid.");
        }
        this.rootLocation = Paths.get(properties.getLocation());

    }

    /***
     * This method is responsible for storing file in the file system
     * @param file - file to store
     * @throws StorageException - if file is empty or failed to store file
     * @see StorageException
     * @see MultipartFile
     * @return
     */

    @Override
    public String store(MultipartFile file) throws StorageException {
        var uuid = UUID.randomUUID();
        var newFileName = "%s.pcap".formatted(uuid);
        try {
            if (file.isEmpty()) {
                LoggerService.error("Failed to store empty file.");
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFilePath = this.rootLocation
                    .resolve(Paths.get(newFileName))
                    .normalize()
                    .toAbsolutePath();

            Path rootAbsolutePath = this.rootLocation.toAbsolutePath();
            Path destinationParentAbsolutePath = destinationFilePath.getParent();

            LoggerService.info(
                    String.format("Storing file %s to %s", file.getOriginalFilename(), destinationFilePath.toString())
            );

            if (!destinationParentAbsolutePath.equals(rootAbsolutePath)) {
                // This is a security check
                LoggerService.error("Cannot store file outside current directory.");
                LoggerService.error("Destination parent path: " + destinationParentAbsolutePath.toString());
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            LoggerService.error("Failed to store file.");
            throw new StorageException("Failed to store file.", e);
        } catch (NullPointerException ex) {
            LoggerService.error("Failed to store file. File is null.");
            throw new StorageException("Failed to store file. File is null.");
        }
        return newFileName;
    }

    /***
     * This method is responsible for loading all files from the file system
     * @return Stream Path - stream of paths
     * @throws StorageException - if failed to read stored files
     * @see StorageException
     */

    @Override
    public Stream<Path> loadAll() throws StorageException {
        try {
            LoggerService.info("Loading all files from the file system.");
            LoggerService.info("Root location: " + this.rootLocation.toString());
            LoggerService.info("Root location absolute path: " + this.rootLocation.toAbsolutePath().toString());
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        LoggerService.info("Loading file: " + filename);
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageFileNotFoundException {
        try {
            Path file = load(filename);

            LoggerService.info("Loading file as resource: " + filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        String.format(
                                "Could not read file: %s",
                                filename
                        )
                );

            }
        } catch (MalformedURLException | StorageFileNotFoundException e) {
            throw new StorageFileNotFoundException(
                    String.format(
                            "Could not read file: %s",
                            filename
                    ),  e
            );
        }
    }

    public List<Packet> loadPackets(String filename) throws StorageFileNotFoundException {
        List<Packet> packets = new ArrayList<>();

        try {
            Path file = load(filename +".pcap");
            Resource resource = new UrlResource(file.toUri());
            LoggerService.info("Loading packets from file: " + filename);

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
            LoggerService.error("Could not read file: " + filename);
            throw new StorageFileNotFoundException(
                    String.format(
                            "Could not read file: %s",
                            filename
                    ),  e
            );
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
            throw new StorageException(
            String.format("Could not initialize storage location: %s",
                    rootLocation.toString()), e);
        }
    }

    public String getRootLocation() {
        return rootLocation.toString();
    }
}
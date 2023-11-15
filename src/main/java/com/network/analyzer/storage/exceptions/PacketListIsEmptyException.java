package com.network.analyzer.storage.exceptions;

public class PacketListIsEmptyException extends RuntimeException  {

        public PacketListIsEmptyException(String message) {
            super(message);
        }
}

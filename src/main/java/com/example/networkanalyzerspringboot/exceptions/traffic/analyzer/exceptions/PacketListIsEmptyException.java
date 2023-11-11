package com.example.networkanalyzerspringboot.exceptions.traffic.analyzer.exceptions;

public class PacketListIsEmptyException extends RuntimeException  {

        public PacketListIsEmptyException(String message) {
            super(message);
        }
}

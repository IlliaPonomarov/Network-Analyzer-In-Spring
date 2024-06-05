package com.network.analyzer.services.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface LoggerService {

     Logger logger = LoggerFactory.getLogger(LoggerService.class);

     static void trace(String message) {
        logger.trace(message);
     }

     static void info(String message) {
        logger.info(message);
     }

     static void debug(String message) {
        logger.debug(message);
     }

     static void error(String message) {
        logger.error(message);
     }

     static void warn(String message) {
        logger.warn(message);
     }
}

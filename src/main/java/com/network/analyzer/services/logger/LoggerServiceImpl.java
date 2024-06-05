package com.network.analyzer.services.logger;


import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoggerServiceImpl implements LoggerService {

    private final Logger logger = LoggerFactory.getLogger(LoggerServiceImpl.class);

    @Override
    public void trace(String message) {
        logger.trace(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }
}

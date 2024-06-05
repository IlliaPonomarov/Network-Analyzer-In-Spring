package com.network.analyzer.services.logger;


public interface LoggerService {

    void trace(String message);
    void info(String message);
    void debug(String message);
    void error(String message);
    void warn(String message);
}

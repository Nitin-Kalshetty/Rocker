package com.backend.rocker.monitoring;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricsLogger {

    private final Logger logger = LoggerFactory.getLogger(MetricsLogger.class);
    private final HikariDataSource hikariDataSource;

    public MetricsLogger(HikariDataSource hikariDataSource){
        this.hikariDataSource = hikariDataSource;
    }

    @Scheduled(fixedDelay = 2000)
    public void logMetrics(){
        Runtime runtime = Runtime.getRuntime();
        long usedMem = (runtime.totalMemory() - runtime.freeMemory())/(1024*1024);
        long maxMem = (runtime.maxMemory());
        logger.info("MEM_USED={}MB | MEM_MAX={}MB | DB_ACTIVE={} | DB_IDLE={} | DB_MAX={}",
                usedMem, maxMem, hikariDataSource.getHikariPoolMXBean().getActiveConnections(),
                hikariDataSource.getHikariPoolMXBean().getIdleConnections(),
                hikariDataSource.getMaximumPoolSize());
    }
}


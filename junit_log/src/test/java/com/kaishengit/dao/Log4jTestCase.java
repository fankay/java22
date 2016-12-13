package com.kaishengit.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTestCase {

    @Test
    public void testLog() {
        Logger logger = LoggerFactory.getLogger(Log4jTestCase.class);

        logger.trace("{}-{} trace message","tom","hello");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");

    }
}

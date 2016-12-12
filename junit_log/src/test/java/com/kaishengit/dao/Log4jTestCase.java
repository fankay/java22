package com.kaishengit.dao;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTestCase {

    @Test
    public void testLog() {
        Logger logger = Logger.getLogger(Log4jTestCase.class);

        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");

    }
}

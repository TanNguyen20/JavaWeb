package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    //Initialize Log4j instance
    private static final Logger Log =  LoggerFactory.getLogger(Log.class);

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.trace(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }

    //Trace Level Logs
    public static void trace (String message) {
        Log.trace(message);
    }

    //Debug Level Logs
    public static void debug (String message, Throwable t) {
        Log.debug(message, t);
    }

    //Trace Level Logs
    public static void trace (String message, Throwable t) {
        Log.trace(message, t);
    }
}

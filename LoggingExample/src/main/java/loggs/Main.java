package loggs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    static Logger log = LogManager.getLogger(Main.class.getName());
//    static  Logger log = LogManager.getRootLogger();
    public static void main(String[] args) {
//        Configurator.setLevel(log.getName(), Level.WARN);
        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
        log.fatal("Fatal Message!");

        new Thread(() -> log.info("Thread info")).start();

    }

}

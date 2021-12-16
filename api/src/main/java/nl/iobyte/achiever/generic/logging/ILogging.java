package nl.iobyte.achiever.generic.logging;

public interface ILogging {

    /**
     * Log info
     * @param message String
     */
    void info(String message);

    /**
     * Log error
     * @param message String
     */
    void error(String message);

}

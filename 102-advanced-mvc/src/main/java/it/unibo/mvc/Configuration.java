package it.unibo.mvc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Encapsulates the concept of configuration.
 */
public final class Configuration {

    private final int max; 
    private final int min;
    private final int attempts;

    public Configuration() {
        int defMin=0;
        int defMax=0;
        int defAtt=0;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.yml") ;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String[] minimum = reader.readLine().split(": ").clone();
            defMin=Integer.parseInt(minimum[1]);
            String[] maximum = reader.readLine().split(": ").clone();
            defMax=Integer.parseInt(maximum[1]);
            String[] att = reader.readLine().split(": ").clone();
            defAtt=Integer.parseInt(att[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.min=defMin;
        this.max=defMax;
        this.attempts=defAtt;
    }

    /**
     * @return the maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * @return the minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the number of attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return attempts > 0 && min < max;
    }
}
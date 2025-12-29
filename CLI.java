import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
* This is my code! Its goal is to process command-line arguments for a search engine program.
* CS 312 - Assignment 8
* @author Angla Tierney 
* @version 1.0 12/15/2024
*/

public class CLI {
    /**
     * Processes command-line arguments.
     *
     * @param args Command-line arguments.
     * @return Parsed arguments in a structured form.
     */
    public static Map<String, Object> processArgs(String[] args) {
        Map<String, Object> parsedArgs = new HashMap<>();
        List<String> documents = new ArrayList<>();
        boolean verbose = false;
        String stopListFile = null;

        for (String arg : args) {
            if ("-v".equals(arg)) {
                verbose = true;
            } else if (stopListFile == null) {
                stopListFile = arg;
            } else {
                documents.add(arg);
            }
        }
        parsedArgs.put("verbose", verbose);
        parsedArgs.put("stopListFile", stopListFile);
        parsedArgs.put("documents", documents);
        return parsedArgs;

    }
}

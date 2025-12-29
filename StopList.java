import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
* This is my code! Its goal is to define a StopList class that loads a list of stopwords from a file
* and provides functionality to check if a word is a stopword.
* CS 312 - Assignment 8
* @author Angela Tierney 
* @version 1.0 -12/15/2024
*/

public class StopList {
    //attributes
    private Set<String> stopWords;
    //has the words

    //constructor
    public StopList(String filePath)  throws IOException {
        this.stopWords = new HashSet<>(Files.readAllLines(Paths.get(filePath)));
    }


    /**
     * Checks if a word is a stopword.
     *
     * @param word Word to check.
     * @return True if the word is a stopword, false otherwise.
     */
    public boolean isStopWord(String word) {
	return stopWords.contains(word);
        //returning the word
        //stopWords has list of stop words
    }





}

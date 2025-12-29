/**
 * This is my code! Its goal is to implement a search engine with stoplist filtering.
 * CS 312 - Assignment 8
 * @author Angela Tierney
 * @version 1 12/15
 */
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SearchEngine {
    //attributes
    private Map<String, Set <Document>> invertedIndex;
    private StopList stopList;

    //constructor
    public SearchEngine(StopList stopList){
        this.invertedIndex = new HashMap<>();
        this.stopList = stopList;
    }

    //build inverted index
    /**
     * Builds the inverted index for the provided documents.
     *
     * @param documents List of documents to be indexed.
     */
    //calling isStopWord
    public void buildInvertedIndex(List<Document> documents) {
        for (Document doc : documents) {
            for (String word : doc) { // Document must implement Iterable<String>.
                if (!stopList.isStopWord(word)) {
                    invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(doc);
                }
            }
        }
    }

    /**
     * Processes a query and retrieves matching documents.
     *
     * @param queryWords Words to search for.
     * @return Set of documents matching the query.
     */
    public Set<Document> query(String[] queryWords) {
        Set<Document> result = new HashSet<>();
        for (String word : queryWords) {
            if (!stopList.isStopWord(word) && invertedIndex.containsKey(word)) {
                result = result.isEmpty() ? new HashSet<>(invertedIndex.get(word)) : intersect(result, invertedIndex.get(word));
            }
        }
        return result;
    }

    /**
     * Prints the inverted index for debugging.
     */
    public void printInvertedIndex() {
        for (Map.Entry<String, Set<Document>> entry : invertedIndex.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    //create a helper method to insert the two sets
    /**
     * Computes the intersection of two sets and returns the result.
     * Modifies the first set to retain only elements that are also in the second set.
     *
     * @param set1 The first set, which will be modified to contain the intersection.
     * @param set2 The second set, whose elements are used to compute the intersection.
     * @return The modified first set containing the intersection of the two sets.
     */
    private Set<Document> intersect(Set<Document> set1, Set<Document> set2) {
        set1.retainAll(set2);
        return set1;
    }

}

import java.util.Iterator;
import java.util.Scanner;

/**
* This is my code! Its goal is to define a Document class that represents 
* a document with a name and content. The class provides functionality to 
* print the content of the document, retrieve the document's name as a string, 
* and iterate over the words in the document.
* CS 312 - Assignment 8
* @author Angela Tierney
* @version 1.0  12/15/2024
*/

public class Document implements Iterable<String> {
    //atttributes
    private String name;
    private String content;

    //constructor
    public Document(String name, String content) {
        this.name = name;
        this.content = content;
    }
    /**
     * Prints the content of the document.
     */
    public void printContent() {
        System.out.println(content);
    }
    /**
     * prints the name of the document 
     */
    @Override
    public String toString() {
        return name;
    }
    /**
* Returns an iterator over the words of the document.
*/
    @Override
    public Iterator<String> iterator() {
        return new Scanner(content).useDelimiter("[^a-zA-Z]+");
    }

}

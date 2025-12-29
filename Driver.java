import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This is my code! Its goal is to implement a simple search engine 
 * that indexes documents, processes queries, and retrieves matching documents 
 * based on an inverted index. It also supports a stoplist to ignore common, 
 * uninteresting words and has an optional verbose mode for displaying document contents.
 * CS 312 - Assignment 8
 * @author Angela Tierney 
 * @version 1.0 12/5/2024
 */

public class Driver {
    public static void main(String[] args) {
	/**
 * The main method that processes command-line arguments, reads document files,
 * builds an inverted index for efficient searching, and handles user input for queries.
 * It supports optional verbose output and special debug commands.
 * 
 * @param args  the command-line arguments passed to the program
 * @throws IllegalArgumentException  if the documents argument is not a List<String>
 */

        try {
            // Getting CLI arguments
            Map<String, Object> cliArgs = CLI.processArgs(args);
            boolean verbose = (boolean) cliArgs.get("verbose");
            String stopListFile = (String) cliArgs.get("stopListFile");

            // Document argument should be a List<String>
            Object documentsObj = cliArgs.get("documents");
            List<String> documentFiles = null;

            // Need to error check if it is a list
            if (documentsObj instanceof List<?>) {
                List<?> tempList = (List<?>) documentsObj;
                // Check if all elements are strings
                if (!tempList.isEmpty() && tempList.get(0) instanceof String) {
                    @SuppressWarnings("unchecked")
                    List<String> castedList = (List<String>) tempList;
                    documentFiles = castedList;
                } else {
                    throw new IllegalArgumentException("Expected a List<String> for 'documents', but the list contains non-String elements.");
                }
            } else {
                throw new IllegalArgumentException("Expected a List<String> for 'documents', but got: " + documentsObj.getClass().getName());
            }

            StopList stopList = new StopList(stopListFile);
            SearchEngine searchEngine = new SearchEngine(stopList);

            List<Document> documents = new ArrayList<>();
            for (String file : documentFiles) {
                String content = new String(Files.readAllBytes(Paths.get(file)));
                documents.add(new Document(file, content));
            }

            searchEngine.buildInvertedIndex(documents);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your queries (Ctrl-D to exit):");
	    long queryStart = System.currentTimeMillis();
            while (scanner.hasNextLine()) {
                String query = scanner.nextLine();
                if ("@@debug".equals(query)) {
                    searchEngine.printInvertedIndex();
                } else {
                    //long queryStart = System.currentTimeMillis();
                    Set<Document> results = searchEngine.query(query.split(" "));
                    int resultCount = (results == null ? 0 : results.size());
                    System.out.println("--- found in " + resultCount + " documents");

                    if (verbose && results != null) {
                        for (Document doc : results) {
                            System.out.println(doc);
                            doc.printContent();
                        }
                    } else if (results != null) {
                        results.forEach(doc -> System.out.println(doc.toString()));
                    }

                    //long queryEnd = System.currentTimeMillis();
                    //System.out.println("@@ Query took " + (queryEnd - queryStart) + "ms");
                }
            }
	     long queryEnd = System.currentTimeMillis();
             System.out.println("@@ Query took " + (queryEnd - queryStart) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

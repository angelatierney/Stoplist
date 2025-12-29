OOD iterations 
ITERATION 1:
----------------------
1. SearchEngine: build inverted index from documents and handle search queries 
attributes 
- invertedIndex: A map that stores words and their associated documents.
- stopList: A reference to a StopList object for stopword filtering.
methods
- stopList: A reference to a StopList object for stopword filtering.
- query(String[]): Searches for documents containing the given query words.
- printInvertedIndex(): Prints the inverted index for debugging.
- intersect(Set<Document>, Set<Document>): Helper to compute the intersection of two sets.
2. Document: Represent a document and provide access to its content as an iterable sequence of words.
attributes
- name: The name of the document.
- content: The text content of the document.
methods 
- iterator(): Provides an iterator to tokenize the content.
- toString(): Returns the name of the document.
- printContent(): Prints the document's content.
3. StopList: Manage stopword filtering.
attributes 
- stopWords: A set containing stopwords loaded from a file.
methods 
- isStopWord(String): Checks if a given word is a stopword.
4. CLI: Process command-line arguments and return them in a structured form.
attributes 
- none
methods 
- processArgs(String[]): Parses CLI arguments into a map with keys like verbose, stopListFile, and documents. 
5. Driver: Serve as the main entry point, integrating all components and running the search engine.

------------------------------
ITERATION 2

 CLI processes user input 
- arguments are parsed and validated before being passed to the driver 
- returns structured data a Map<String, Object>, including:
	- verbose: A flag for detailed output.
	- stopListFile: The stopword file path.
	- documents: A list of document file paths.
Driver: validates inut and initializes objects: 
- converts file paths into Document object 
- constructs the SearchEngine with a StopList 
SearchEngine handles queries 
- filters out stopwords before precesssing the query 
- supports debugging wiht the @@debug command to print the index 



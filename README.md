# CS312 - Assignment 8 - Let me just 312 that for you
OOA due: 12/5  
Final due: 12/15

## Goals
This assignment will give you practice with:
1. Maps
2. Sets
3. OOA/OOD

## Problem Statement
Oh no! You have time traveled back to the early days on the internet. As you are trying to find your way back, a person who introduced themselves as "Sergey" tells you that they and their friend "Larry" need some help. They think there must be a way to find information on the world wide web, but there is no good way to search it. Sergey and Larry think maybe you could help them create a *search engine*. Since not all words will be interesting, they also want a *stoplist* and to know how long the whole thing takes to run queries.

## Requirements Elicitation
Q: What is a search engine?  
A: A search engine performs extremely fast retrieval of documents (e.g., files) at the expense of a
somewhat slow setup phase. Setup builds a mapping that associates each word found in any of the
documents with the collection of documents that include that word. The resulting data structure is
known as an *inverted index*.

Q: What happens when a query is issued?  
A: When a query is issued, a collection of documents is retrieved. For a single word query, the collection is those documents associated with the query word by the inverted index. For multi-word
queries, the collection is those documents associated with all the words of the query (i.e., those
documents that include all of the query words).  

Q: What is a *stoplist*?  
A: Very common words such as 'the' will likely appear in every document. That sort of uninteresting word
is known as a *stopword*. Rather than wasting time indexing and retrieving documents associated
with stopwords, the stopwords are never indexed and ignored when in a query. This means that a query consisting of only stopwords will result in no documents being retrieved, but if a stopword is included among non-stopword words, then the stopword is simply ignored. For example, if the word 'the' is on the stoplist while 'sponge' is not then the queries 'the sponge' and 'sponge' will return the same result.

Q: What command-line arguments and inputs should the program to take?  
A: **(command-line arguments)** The CLI for your search engine should take as parameters an optional
'verbose' flag, a stoplist filename, and the list of documents to be indexed. If present, the
'verbose' flag, -v, indicates that the contents of the documents should be displayed along
with their names. In the absence of this flag, only the document names should be displayed.  
**(inputs)** Post indexing, searching is supported by having the user enter queries (i.e., queries are
read from standard in). Pressing enter executes a query. Ctrl-D terminates the program.

Q: What should the output be?  
A: The names of the retrieved documents should be printed (to standard out). If the -v
flag is given then the output should include the contents of each retrieved document, output in its
original format (e.g., with whitespace preserved). It should also output the total number of files found for each query.
When all queries are complete (i.e., Ctrl-D is encountered), the program should output the total time.

Q: What about numbers and stuff?  
A: While punctuation should be maintained in the retrieved documents, it should not be included
when comparing words. For example, if asked "Does the previous sentence include 'words'?", the
answer is yes, although the actual string is 'words.'. Treat non-alphabetic characters as whitespace.

Q: Are there any special queries to support?  
A: The special query `@@debug` should print the current inverted index.


## Design Requirements
0. Your program must run as `java Driver [-v] stoplistfile document1 ... documentN`
1. You must use hashing to store the inverted index. Recall that the inverted index associates a key
(a word) with the collection of documents that contain that key.
2. Your program must store the stoplist using hashing to support the expected O(1) look-up time.
3. Your data structures must include at least a `HashMap` and a `HashSet`. You can, of course, use others beside those.
4. You must include in your code:  
```java
System.out.println("--- found in " + (docs == null ? 0 : docs.size()) +"documents");
```
5. Timing should be done over each query, though not while waiting for user input. The total time should be printed 
when all queries are complete. As a reminder, code such as the below will help you time things. The "@@" symbol will make it easy to find the timing line.
```java
long startTime = System.currentTimeMillis();
// do something
long stopTime = System.currentTimeMillis();
long elapsedTime = stopTime - startTime;
System.out.println("@@ 'something' took " + elapsedTime + "ms");
```


## Test Examples
1. An example test case
```
Input: the command "java CLI stoplist colors.txt cats.txt" where
stoplist contains ""
colors.txt contains "red blue yellow orange green purple pink white"
cats.txt contains "Miles is a white cat"
and the query is "white"
Expected Output: colors.txt cats.txt
Rationale: check that the program returns two file names when the query word is present in
both files.
```
2. Test queries, documents, and a stoplist are stored at: `/home/class_projects/retrieval-data`. To test your code try:
```bash
java Driver /home/class_projects/retrieval-data/stoplist /home/class_projects/retrieval-data/docs/* < /home/class_projects/retrieval-data/queries1 | grep -e "---" > my_out
diff my_out /home/class_projects/retrieval-data/queries1.out
```
-> The only difference in the files should be the retrieval times at the bottom of the file.

## Useful Java Things
1. The Java class `Path` can extract the file name from a complete path (e.g., extract `t.java` from `/home/ho/src/t.java`).  
```java
import java.nio.file.Paths;
import java.nio.file.Path;
...
Path p = Paths.get(completePath);
String fileName = p.getFileName().toString();
```
2. Read an entire file in one read.   
```java
try
{
   asRead = new String(Files.readAllBytes(Paths.get(filename)));
}
catch (Exception ex)
{
...
}
```
3. Return an iterator that iterates over the words of a document.
```java
// A string representation of the whole document. (See tip 2)
String DocAsRead;
...
/**
* Returns an iterator over the words of the document.
*/
public Iterator<String> iterator()
{
    return new Scanner(DocAsRead).useDelimiter("[ˆa-zA-Z]+");
}
// a for loop can now be used to iterate over the words:
// for(String word : doc)
```

## Stylistic Requirements
In any company, you will be required to adhere to particular stylistic conventions. Such is true here too. Please ensure that your code adheres to the following.

1. Use protected or private for all attributes. Make sure to only refer to attributes in their declaring class and its subclasses.
2. Each source code file must start with (replace the bits in < >):
```java
/**
* This is my code! Its goal is to <give purpose of file here>
* CS 312 - Assignment 8
* @author <Your Name>
* @version <a version number followed by a date>
*/
```
3. Use clear documentation and careful formatting. Be consistent in indentation and alignment of braces.
4. Every method must have a header comment of the form (replace the bits in < > and only use the number of @param that are needed for your method):
```java
/**
* <A one sentence description of the method, ending with a period.>
* <Optional longer description if desired>
*
* @param  <first parameter name>  <purpose of the parameter>
* @param  <second parameter name>  <purpose of the second parameter>
* @return      <what is returned>
*/
```
5. Your code must compile with no warnings using javac -encoding UTF-8 -source 8.
6. Avoid wildcards in imports such as import java.util.*
7. Your code can contain no more than one static method.
8. Avoid the use of the instanceof keyword. True OO thinking should not require it..
9. All concrete classes must include reasonable toString methods.
10. Your code should contain no get() or set() methods.
11. Be mindful of line breaks in your code. To see how your code will look to me, run:
```bash
a2ps -T 4 -q -Avirtual -2 -o mycode.ps *.java
ps2pdf mycode.ps
xpdf mycode.pdf
```
12. Do not commit derivable files such as .class files.

## Analysis Submission
To GitHub by 12/5:
1. a pdf of your UML class diagram
2. OOA.md containing well formatted OOA iterations. Make sure that you follow the OOA process taught in this course. You might need to refer back to your notes for a quick refresher.

## Final Submission
To GitHub by 12/15:
1. All relevant source code.
2. A list of OOD iterations in `OOD.md`.
3. A list of milestones in `Milestones.md`
4. A list of your tests in `Testing.md`
5. A general reflection about the project in `Reflection.md`


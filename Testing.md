Angela Tierney 

1. 
input: command java Driver stoplist colors.txt cats.txt
- stoplist contains nothing in the text file 
- colors.txt contains "red blue yellow orange green purple pink white"
- cats.txt contains "Miles is a white cat"
queries: white
expected: should return both files that contain the word
 
2. 
input command java Driver -v stoplist.txt colors.txt cats.txt
- stoplist constains nothing 
- colors.txt contains "red blue yellow orange green purple pink white"
- cats.txt contains "Miles is a white cat"
queries: white 
expected: returns the files contents that both have the same word

3. 
input command: java Driver -v stoplist.txt colors.txt cats.txt hi.txt
- - stoplist constains nothing
- colors.txt contains "red blue yellow orange green purple pink white"
- cats.txt contains "Miles is a white cat"
- hi.txt: contains:i said hi to the cat Miles
queries: Miles
expected: returns the files and the files contents for the ones with the same word    

4. 
input command: java Drive stoplist.txt colors.txt bob.txt 
- stoplist constains nothing
- colors.txt contains "red blue yellow orange green purple pink white"
- bob.txt contains "bobs phone is green and white" 
queries: green 
expected output: return the documents that have the same word 

5.input command: java Drive -v stoplist.txt colors.txt bob.txt cat.txt hi.txt
- stoplist constains nothing
- colors.txt contains "red blue yellow orange green purple pink white"
- bob.txt contains "bobs phone is green and white"
- hi.txt: contains:i said hi to the cat Miles
queries: white 
exected: return the files with the same word and with the conent in those files


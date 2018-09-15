## Java Homework Four
### Histogram 1.0
The purpose of this assignment is for you to learn to work with files and exceptions. This assignment will be the basis for your next few assignments. The next assignment will make your code portable via a class. Then the assignment after that will create a subclass of your new class.

Create a Java program that will read in a list of integers from a file and output a histogram.

The name of the file should come from the user.
The distribution size of each bar in the graph should be 10.
Start the graph at value 0 and end at no more than 99.

## Sample Run
```
$ java lab04
Name of file to process: integers.txt
0-9    ***
10-19
20-29  **
30-39  ****
40-49  *
```
#### The above output would be the result of processing this file, named integers.txt:
```
3
5
8
28
29
30
31
Hi
Mom
45
32
33
```
### Exceptions to Handle
If the user inputs the name of a file that does not exist or can't be opened for reading, report the error and exit.
If the file contains text, just ignore the text. Be careful that you don't count the text as a zero and that text does not cause the previous value to appear in the graph twice.

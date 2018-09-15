# Java Homework Three
#### Arrays and Files
In class we looked at some code to read numbers from a file and sum up those values.  The fourth version of that program asks the user for a file name, then reads each line of the file, converts each line to an integer, and sums all the values.

Edit that program to include two new features.  First, if the file contains non-integers, then just ignore those lines.  Second, print each of the integers in order.  To implement the first change, you will need to catch a new excecption.  To implement the second change, you will need an array.  Here is the tricky part : do NOT create an array that is far too big.

## Here is a sample run:
```
$ cat data.txt
12
55
Hi Mom
7
$ java hw03
Name of file to process: data.txt

The sum of the integers
7
12
55
is 74.
```

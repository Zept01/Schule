******************************************************
	IPv4 Validator and Class-Check
		by Pascal Bauer
******************************************************

### How does it work?

1.The program expects an IPv4 address from the user
2.The Ip gets validated and the first Byte is converted to binary
3.At the end, the network class of the respective IP gets displayed

### What is a valid IPv4 address

A valid IPv4 address always looks like this: xx.xx.xx.xx
where xx is an integer between 0 and 255

### What does an invalid IPv4 address look like?

1.1.1        (only 3 decimal numbers)
1.1.1.       (ending with a dot)
1.1..1       (two consecutive dots)
.1.1.1       (starting with a dot)
1.1.1.a      (containing an alphabet)
172.8.9.256  (decimal number exceeds 255)
172.8.-9.255 (negative decimal number)
172.8.9.266  (decimal number exceeds 266)
172.013.1.2  (contains octal number 013)
172.a.1.2    (contains hexadecimal number a)
.
.
.

### How to start the program? 

Import IPv4Validator.java into an Javaproject and run the main Method

--> If you are just wondering what the output looks like, have a look at Results.png
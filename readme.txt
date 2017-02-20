IP Validator Readme
===================
This program is written in Java 8.

ASSUMPTIONS
===========
1. Assumed that Java SE 8 is installed on your machine.
2. The following type of IPv4 and IPv6 addresses were considered as valid:
	- Private IP Addresses
	- Public IP Addresses
	- Broadcast IP Addresses
	- Multicast IP Addresses
	- Anycast IP Addresses

COMPILING & EXECUTING THE PROGRAM
=================================
1. Unzip the IPValidator.zip file in any folder on your local drive
2. Open the terminal and navigate to the folder where the contents of the unzipped file is located.
3. Type: 'javac src/IPValidator.java' to compile the file.
4. Type: 'java src/IPValidator < {input filename}' to execute the program. 
   (E.g. Type 'java src/IPValidator < input1.txt')
5. After the program stated that it is finished it has created two files for valid
   and invalid IP addresses called 'valid.txt' and 'invalid.txt' respectively.
   
   
*****
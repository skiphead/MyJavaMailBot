# MyJavaMailBot
This is my first program in Java. 
Created to develop programming skills and automate some routine tasks. 
If the program will be useful to you, please put a star :-)

Stable running on the JDK 8

How to use?

1.  Configure json file conf/config.json
2.  Configure access config  conf/access.cfg
3. Run Main.java

How it works?

MyMailJavaBot continuously checks for new messages on the imap protocol. If the incoming message was received and the sender is in the access.cfg list, then Robot.java parses the message body and checks the conditions in the SWITCH after it has completed the corresponding task or the specified system command if the command is not defined by default, bot sends the response to the recipient that don’t know such commands.

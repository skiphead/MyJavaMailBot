# How to use?

1.  Configure json file conf/config.json
2.  Configure access config  conf/access.cfg
3. Run Main.java

# How it works?

MyMailJavaBot continuously checks for new messages on the imap protocol. If the incoming message was received and the sender is in the access.cfg list, then Robot.java parses the message body and checks the conditions in the SWITCH after it has completed the corresponding task or the specified system command if the command is not defined by default, bot sends the response to the recipient that don’t know such commands.

# Example

For a visual example, the bot can send a message in the body of the letter by writing the “help me” command, in response the bot will send a manpage from conf/man.page.

# PS:

This is my first java-program.
Designed for pumping programming skills in Java and automating some routine tasks.
If the program for you is a program, please put a star :-)

Stable work on JDK 8

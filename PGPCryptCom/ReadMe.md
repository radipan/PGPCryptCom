1. Program startup: done
1.1 Loading configuration
- 
1.2 Initializing logging
1.3 Setting up log level
1.4 Loading dependencies
1.5 Loading working libraries
1.6 Generating folder structure for the application
2. Executing routine: As user I want to generate keys
   2.0 Preconditions
    2.0.1 The user is typing e-mail adress in UI form
    2.0.2 The user is selecting Key type
    2.0.3 The user is typing password in UI form
   2.1 Generating PGP certificate
   2.2 Generating RSA subkey
   2.3 Creating PGP certificate file
3. Executing routine: As user I want to import a public keys from a files
   3.0 Preconditions:
    3.0.1 Another user is delivering a public key to the communication party
    3.0.2 For testing purposes we are generating a pub and ppk with puttygen
   3.1 Reading file
   3.2 Checking file format
   3.3 Processing key information
4. Executing routine: As user given step 2 and 3 is complete I want to encrypt a message
   4.0 Preconditions:
    4.0.1 Load UI for inputing message
    4.0.2 Label, input box, subject, body message, receiver email address, send button
   4.1 Running send event: user is clicking on the button Send
   4.2 Checking email adress in imported public keys
   4.3 Encrypting message from body message using public key and encryption type from the imported key file
   4.4 Writing encrypted message to file
5. Executing routine: Given I have an encrypted message I need to send it to the recipient 
   5.0 Preconditions:
    5.0.1 Initialize an SMTP client
    5.0.2 Load SMTP settings
   5.1 Assign to the SMTP client the email adress of the recipient
    5.1.1 If offline and for testing purpose we will use a folder structure as an input and output to simulate message sending and delivery
   5.2 Assign the body to the SMTP client
    5.2.1 For offline purpose we will use a File handler class to read and write folder structure
   5.3 Try delivering the message to the recipient email adress
    5.3.1 Verifying message is in the inbox folder of the recipient
6. Executing routine: Given step 5 is complete, as a recipient I want to decrypt the recieved message
   6.0 Preconditions:
    6.0.1 Open recipient UI form
    6.0.2 Be notified that a message has arrived (File watcher)
   6.1 Loading recieved message from the file in the recipient inbox
   6.2 Loading recipient private key
   6.3 Detecting encryption type
   6.4 Decrypt message
   6.5 Write message to Inbox/Decrypted messages
   6.6 Load Decrypted message in the UI form
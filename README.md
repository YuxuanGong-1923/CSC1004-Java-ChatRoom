# CSC1004-Java-ChatRoom
  
This code implements the basic program of Java Chatroom
which can implement the function of GUI, multi-client chat, 
login and register.

Instructions on how to run this code:

  First, download a xampp control panel which can control the database
  easily. I don't know whether this program can work on TA's desktop with 
  mysql desktop. If not, I'm sorry to trouble TA to download a new app.
  Here are two videos on YouTube teaching how to download them

  Second, you start the code and then click the server button to start the
  server. Don't close the server after it start running until the chat ends.

  Third, you open the program again, enter the Client mode
  click the register button to register. 

  Fourth, you start the program and enter the client mode again to login in.
  After successfully login, you can see a chat page.

  Fifth, you start the program again and enter numerous clients to have
  multi-client chat.

Techniques I have used and how I developed this project
  To implement the login and register system, I refer to a video on YouTube
  Using the GUI inside intelliJ to design the UI 
  and use XAMPP to control mysql

  To implement the function of multi-client chat, I refer to the sample 
  code offered in the course page.
  The project has a server mode and a client mode.
  The SERVER program receives the login message from client 
  and once confirmed the client's information valid, 
  it will create a new clientHandler thread
  The CLIENT HANDLER program handles with the message from client 
  and broadcast them.
  The ChatPage program connects with the chat page GUI and get the input
  from the user.
  
  

package com.CSC1004.client.service;

import com.CSC1004.common.Message;
import com.CSC1004.common.MessageType;
import com.CSC1004.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class UserClientService {
    private User u = new User();
    private Socket socket;
    public boolean checkUser(String userId,String pwd){
        Boolean b = false;
       u.setUserId(userId);
       u.setPasswd(pwd);

        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                //Create a Thread to keep communicate with the Server
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                ManageClientConnectServerThread.addClientConnectServerThread(userId,clientConnectServerThread);
                b = true;
            }
            else{
                socket.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b;
    }
}

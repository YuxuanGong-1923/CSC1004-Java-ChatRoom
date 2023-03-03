package com.CSC1004.client.service;

import com.CSC1004.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;
    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }
    public void run(){

        super.run();

        while(true){
            try {
                System.out.println("客户端线程等待读取从服务器段发送的信息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();//如果服务器没有发送Message对象，线程会阻塞
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Socket getSocket() {
        return socket;
    }
}

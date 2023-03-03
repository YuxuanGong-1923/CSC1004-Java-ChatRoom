package com.CSC1004.client.service;

import java.util.HashMap;

public class ManageClientConnectServerThread {
    //Put Multi-Threads into a HashMap.
    //Key is the UserId
    //Value is the Thread
    private static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();
    public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread){
        hm.put(userId,clientConnectServerThread);
    }
    // Use userId to get Corresponding Thread
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }
}

package com.CSC1004.client.view;

import com.CSC1004.client.utils.Utility;

public class View {

    private boolean loop = true;//Control whether show 1st menu
    private String key = "";//Receive the keyboard input
    private void mainMenu(){

        while(loop){

            System.out.println("Login or Exit");

            key = Utility.readString(1);// Input a number to decide in or exit

            switch(key){
                case "1":
                    System.out.println("1st Menu");
                    String userId = Utility.readString(50);
                    String pwd = Utility.readString(50);
                    //Check whether the Client exist in the Server
                    //Create Class UserClientService
                    if(true){
                        System.out.println("2nd Menu");
                    }
                    else{
                        System.out.println("Login Fails");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}

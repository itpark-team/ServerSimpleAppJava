package com.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    static void Log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket;
        Socket socket;

        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;

       // serverSocket = new ServerSocket(23465, 1);
        serverSocket = new ServerSocket(23465, 1, InetAddress.getByName("127.0.0.1"));

        Log("server created and started");

        socket = serverSocket.accept();
        Log("client is connected");

        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String msgFromClient = dataInputStream.readUTF();
        Log("msg from client: " + msgFromClient);

        String msgToClient = msgFromClient + " получено111";

        dataOutputStream.writeUTF(msgToClient);
        dataOutputStream.flush();
        Log("msg to client: " + msgToClient);

        dataInputStream.close();
        dataOutputStream.close();

        socket.close();
        serverSocket.close();

        Log("server stopped");
    }

}

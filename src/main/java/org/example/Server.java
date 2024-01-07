package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3003);
            Socket localSocket = serverSocket.accept();

            DataInputStream dataInputStream =
                    new DataInputStream(localSocket.getInputStream());
            DataOutputStream dataOutputStream =
                    new DataOutputStream(localSocket.getOutputStream());
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply ;

            while (!message.equals("end")) {
                message = dataInputStream.readUTF();
                System.out.println("Client : " + message);

                System.out.print("You : ");
                reply = bufferedReader.readLine();

                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();
            }
            localSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

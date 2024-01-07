package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket remotesocket = new Socket("localhost", 3003);

            DataOutputStream dataOutputStream =
                    new DataOutputStream(remotesocket.getOutputStream());
            DataInputStream dataInputStream =
                    new DataInputStream(remotesocket.getInputStream());
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply ;

            while (!message.equals("end")) {
                System.out.print("You : ");
                reply = bufferedReader.readLine();

                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();

                reply = dataInputStream.readUTF();
                System.out.println("Server : " + reply);
            }
            remotesocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.hust.soict.tannp.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9898);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        System.out.println(reader.readLine());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();

            if (message.equals("")) {
                break;
            } else {
                writer.println(message);

                System.out.println(reader.readLine());
            }
        }

        scanner.close();
        socket.close();
    }
}

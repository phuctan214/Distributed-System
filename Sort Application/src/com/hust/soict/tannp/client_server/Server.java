package com.hust.soict.tannp.client_server;

import com.hust.soict.tannp.helper.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {

    public static void main(String[] args) {
        System.out.println("The Sorter Server is running");

        int clientNumber = 0;

        try (ServerSocket listener = new ServerSocket(9898)) {
            while (true) {
                new Sorter(listener.accept(), clientNumber++).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Sorter extends Thread {

        private Socket socket;
        private int clientNumber;

        private BufferedReader reader;
        private PrintWriter writer;

        private NumberSort numberSort;

        public Sorter(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            this.numberSort = new BubbleSort();
            System.out.println("New client #" + clientNumber + " connected at " + socket);
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);

                writer.println("Hello, you are client #" + clientNumber);

                while (true) {
                    String input = reader.readLine();

                    if (isInputBlank(input)) {
                        break;
                    }

                    String[] nums = input.split(" ");
                    convertInputToIntArray(nums);

                    int[] intInputArr = convertInputToIntArray(nums);

                    numberSort.sort(intInputArr);

                    String sortedArrayOutput = convertIntArrayToOutput(intInputArr);

                    writer.println(sortedArrayOutput);
                }
            } catch (IOException e) {
                System.out.println("Error handling client #" + clientNumber);
            } finally {
                handleSocketClose(socket, clientNumber);
            }
        }

        private boolean isInputBlank(String input) {
            return input == null || input.isEmpty();
        }

        private int[] convertInputToIntArray(String[] nums) {
            int[] intArr = new int[nums.length];

            int i = 0;

            for (String textValue : nums) {
                intArr[i] = Integer.parseInt(textValue);
                i++;
            }

            return intArr;
        }

        private String convertIntArrayToOutput(int[] intArr) {
            String[] strArray = Arrays.stream(intArr)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);

            return Arrays.toString(strArray);
        }

        private void handleSocketClose(Socket socket, int clientNumber) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Connection with client #" + clientNumber + " closed");
        }
    }
}

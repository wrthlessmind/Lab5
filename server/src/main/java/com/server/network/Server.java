package com.server.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;

public class Server {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("server");
        int port = Integer.parseInt(bundle.getString("SERVER_PORT").trim());

        System.out.println("Сервер запускается...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порте " + port + "!");
            while (true) {
                System.out.println("Ожидание подключения...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress().getHostAddress());
                new ClientThread(clientSocket).run();
                System.out.println("Клиент отключился.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
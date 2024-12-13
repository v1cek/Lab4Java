// UDP Server
import java.net.*;

public class SimpleUDPServer {
    public static void main(String[] args) {
        int port = 12345; // Порт сервера

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("UDP-сервер запущено на порті " + port);

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                // Очікуємо отримання повідомлення
                serverSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Отримано: " + receivedMessage);

                // Якщо отримано "exit", сервер завершує роботу
                if (receivedMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Сервер завершує роботу.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Помилка сервера: " + e.getMessage());
        }
    }
}

// UDP Client
import java.net.*;
import java.util.Scanner;

public class SimpleUDPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Адреса сервера
        int port = 12345; // Порт сервера

        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("UDP-клієнт запущено. Введіть повідомлення для відправлення (введіть 'exit' для завершення):");

            while (true) {
                String message = scanner.nextLine();

                byte[] sendBuffer = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName(serverAddress), port);

                // Відправка повідомлення серверу
                clientSocket.send(sendPacket);

                // Завершити, якщо користувач ввів "exit"
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Клієнт завершив роботу.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Помилка клієнта: " + e.getMessage());
        }
    }
}
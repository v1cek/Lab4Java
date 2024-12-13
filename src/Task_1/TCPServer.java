import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 12345; // Порт, який прослуховує сервер

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущено, очікування підключення на порті " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Клієнт підключився: " + clientSocket.getInetAddress());

                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        System.out.println("Отримано від клієнта: " + clientMessage);

                        // Відправляємо відповідь клієнту (сервіс ехо)
                        out.println("Сервер: " + clientMessage);

                        // Завершити, якщо клієнт надсилає "exit"
                        if (clientMessage.equalsIgnoreCase("exit")) {
                            System.out.println("Клієнт завершив з'єднання.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Помилка зв'язку: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Не вдалося запустити сервер: " + e.getMessage());
        }
    }
}
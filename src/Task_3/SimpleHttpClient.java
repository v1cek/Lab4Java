// HTTP Client Example
import java.net.http.*;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

public class SimpleHttpClient {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Виберіть тип запиту: 1 - GET, 2 - POST, 0 - Вийти");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очищення буфера

            try {
                if (choice == 1) {
                    System.out.print("Введіть URL для GET-запиту: ");
                    String url = scanner.nextLine();

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

                    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

                    System.out.println("Статус код: " + response.statusCode());
                    System.out.println("Заголовки: " + response.headers());
                    System.out.println("Тіло відповіді: \n" + response.body());

                } else if (choice == 2) {
                    System.out.print("Введіть URL для POST-запиту: ");
                    String url = scanner.nextLine();

                    System.out.print("Введіть дані для відправки: ");
                    String data = scanner.nextLine();

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .POST(HttpRequest.BodyPublishers.ofString(data))
                            .header("Content-Type", "application/json")
                            .build();

                    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

                    System.out.println("Статус код: " + response.statusCode());
                    System.out.println("Заголовки: " + response.headers());
                    System.out.println("Тіло відповіді: \n" + response.body());

                } else if (choice == 0) {
                    System.out.println("Вихід з програми.");
                    break;
                } else {
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (Exception e) {
                System.err.println("Помилка при виконанні запиту: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

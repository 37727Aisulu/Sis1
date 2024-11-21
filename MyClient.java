import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Введите имя объекта Circle или Rectangle или Q для выхода: ");
                String shape = scanner.nextLine();
                if (shape.equalsIgnoreCase("Q")) {
                    break;
                }
                switch (shape) {
                    case "Circle":
                        System.out.print("Введите радиус: ");
                        double radius = scanner.nextDouble();
                        scanner.nextLine();
                        Circle circle = new Circle(radius);
                        oos.writeObject(circle);
                        break;
                    case "Rectangle":
                        System.out.print("Введите длину: ");
                        double length = scanner.nextDouble();
                        System.out.print("Введите ширину: ");
                        double width = scanner.nextDouble();
                        scanner.nextLine();
                        Rectangle rectangle = new Rectangle(length, width);
                        oos.writeObject(rectangle);
                        break;
                    default:
                        System.out.println("Неизвестный тип фигуры. Попробуйте снова.");
                        continue;
                }
                String response = (String) ois.readObject();
                System.out.println("Сообщение от сервера: " + response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

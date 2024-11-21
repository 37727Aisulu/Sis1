import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен и ожидает подключений");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен");
                try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
                    while (true) {
                        Object object = ois.readObject();
                        if (object == null) break;
                        if (object instanceof Circle) {
                            Circle circle = (Circle) object;
                            double area = circle.calculateArea();
                            oos.writeObject("Ответ: " + area);
                        } else if (object instanceof Rectangle) {
                            Rectangle rectangle = (Rectangle) object;
                            double area = rectangle.calculateArea();
                            oos.writeObject("Ответ: " + area);
                        } else {
                            oos.writeObject("Неизвестный тип объекта");
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


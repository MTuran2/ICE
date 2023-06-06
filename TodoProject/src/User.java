import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private List<String> todoList;

    public static User createUser(Scanner scanner) {
        System.out.println("Indtast brugernavn:");
        String username = scanner.nextLine();

        System.out.println("Indtast adgangskode:");
        String password = scanner.nextLine();

        User user = new User(username, password);
        user.saveToFile();

        System.out.println("Bruger oprettet!");
        return user;
    }

    public static User login(Scanner scanner) {
        System.out.println("Indtast brugernavn:");
        String username = scanner.nextLine();

        System.out.println("Indtast adgangskode:");
        String password = scanner.nextLine();

        // Authenticator
        try (BufferedReader reader = new BufferedReader(new FileReader("TodoProject/Data/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String storedUsername = parts[0];
                String storedPassword = parts[1];

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    System.out.println("Login vellykket!");
                    return new User(username, password);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af bruger.");
        }

        System.out.println("Ugyldigt brugernavn eller adgangskode.");
        return null;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.todoList = new ArrayList<>();
    }

    public void addTodoItem(Scanner scanner) {
        System.out.print("Indtast opgave: ");
        String task = scanner.nextLine();
        todoList.add(task);
        System.out.println("Opgave tilføjet.");
        System.out.println();
    }

    public void removeTodoItem(Scanner scanner) {
        System.out.print("Indtast opgave at fjerne: ");
        String task = scanner.nextLine();
        if (todoList.remove(task)) {
            System.out.println("Opgave fjernet.");
        } else {
            System.out.println("Opgave ikke fundet.");
        }
        System.out.println();
    }

    public void listTodoItems() {
        System.out.println("OPGAVER : ");
        if (todoList.isEmpty()) {
            System.out.println("Ingen opgaver fundet.");
        } else {
            for (String task : todoList) {
                System.out.println(task);
            }
        }
        System.out.println();
    }

    public void saveToFile() {
        String filename = "TodoProject/Data/users.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(username + "," + password);
            System.out.println("Brugeren er gemt i filen.");
        } catch (IOException e) {
            System.out.println("Fejl. Kan ikke gemme brugeren i filen: " + e.getMessage());
        }
    }

}

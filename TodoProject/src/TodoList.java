import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoList {
    private List<String> todoList;

    public TodoList() {
        todoList = new ArrayList<>();
    }

    public void saveTodoListToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String task : todoList) {
                writer.println(task);
            }
            System.out.println("Opgaver er gemt i filen.");
        } catch (IOException e) {
            System.out.println("Fejl. Kan ikke gemme opgaverne i filen: " + e.getMessage());
        }
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        boolean userAuthenticated = false;

        System.out.println("Velkommen! Vælg en af følgende muligheder:");
        System.out.println("1. Login");
        System.out.println("2. Opret ny bruger");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            user = User.login(scanner);
            userAuthenticated = (user != null);
        } else if (choice.equals("2")) {
            user = User.createUser(scanner);
            userAuthenticated = true;
        } else {
            System.out.println("Ugyldigt valg. Programmet afsluttes.");
            return;
        }

        if (userAuthenticated) {
            Menu menu = new Menu();
            TodoList todoList = new TodoList();

            boolean running = true;

            while (running) {
                menu.printMenu();
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        user.addTodoItem(scanner);
                        break;
                    case "2":
                        user.removeTodoItem(scanner);
                        break;
                    case "3":
                        user.listTodoItems();
                        break;
                    case "4":
                        todoList.saveTodoListToFile("data/opgaver.txt");
                        running = false;
                        System.out.println("Udført");
                        break;
                    default:
                        System.out.println("Ugyldigt valg. Prøv igen.");
                        break;
                }
            }

            user.saveToFile();
        } else {
            System.out.println("Brugernavn eller adgangskode er ugyldigt. Programmet afsluttes.");
        }
    }
}

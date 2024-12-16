package com.example.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Hello world!
 */
public class App {

    private static final Path TASK_FILE_PATH = Paths.get("tasks.csv");
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};

    private static String[][] tasks;

    private static Scanner scanner;


    public static void main(String[] args) {
        init();

        while (true) {
            printOptions(OPTIONS);
            final var input = scanner.nextLine();
            switch (input) {
                case "add" -> add();
                case "remove" -> remove();
                case "list" -> list();
                case "exit" -> exit();
                default -> System.out.println("Please select a correct option.");
            }
        }
    }

    private static void init() {
        loadTasks();

        scanner = new Scanner(System.in);

        // Logo
        System.out.println(ConsoleColors.GREEN_BOLD + "-----------------");
        System.out.println(ConsoleColors.GREEN_BOLD + "Task manager");
        System.out.println(ConsoleColors.GREEN_BOLD + "-----------------");
    }

    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option: " + ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
        System.out.print(ConsoleColors.RESET);
    }

    private static void list() {
        System.out.println(ConsoleColors.PURPLE + "Id:[Note, Date, IsImportant ]");
        System.out.println(ConsoleColors.RESET + "-----------------");
        for (int i= 0; i < tasks.length; i++) {
            System.out.println( i + ":"  + Arrays.toString(tasks[i]));
        }
    }

    private static void add() {
        final var task = new String[3];

        System.out.println("Task name:");
        task[0] = scanner.nextLine();

        System.out.println("Task date [yyyy-mm-dd]:");
        task[1] = scanner.nextLine();

        System.out.println("Task is important [true/false]: :");
        task[2] = scanner.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;
    }

    private static void remove() {
        list();
        while(true){
            System.out.println("Select id task for delete:");
            final var id = scanner.nextInt();

            if(id>0 && id< tasks.length){
                tasks = ArrayUtils.remove(tasks, id);
                break;
            } else {
                System.out.println("Please select a valid id.");
            }
        }
    }

    private static void exit() {
        saveTask();
        scanner.close();
        System.exit(0);
    }

    private static void loadTasks() {
        try {
            final var lines = Files.readAllLines(TASK_FILE_PATH);

            tasks = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                tasks[i] = lines.get(i).split(", ");
            }
        } catch (IOException e) {
            System.err.println("Failed to load tasks.csv");
            e.printStackTrace(System.err);
        }

    }

    private static void saveTask() {
        final var sb = new StringBuilder();

        for (var task : tasks) {
            sb.append(String.join(", ", task));
            sb.append("\n");
        }

        try {
            Files.writeString(TASK_FILE_PATH, sb.toString());
        } catch (IOException e) {
            System.err.println("Failed to save tasks.csv");
            e.printStackTrace(System.err);
        }

    }
}

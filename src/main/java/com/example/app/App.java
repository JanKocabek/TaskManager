package com.example.app;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    private static String[][] tasks;

    private static Scanner scanner;

    public static void main(String[] args) {
        init();

        while (true) {
            System.out.println(ConsoleColors.BLUE + "Please select an option: ");
            System.out.print(ConsoleColors.RESET);
            final var input = scanner.nextLine();

            switch (input) {
                case "add" -> add();
                case "remove" -> remove();
                case "list" -> list();
                case "exit" -> exit();
                default -> System.out.println("Invalid option");
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

    private static void list() {
    }

    private static void add() {
    }

    private static void remove() {

    }

    private static void exit() {
        saveTask();
        scanner.close();
        System.exit(0);
    }

    private static void loadTasks() {

    }

    private static void saveTask() {

    }
}

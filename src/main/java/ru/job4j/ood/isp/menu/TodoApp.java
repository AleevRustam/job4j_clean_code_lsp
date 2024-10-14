package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {

    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private final  Menu menu;

    public TodoApp(Menu menu) {
        this.menu = menu;
    }

    public void addRootItem(String name) {
        menu.add(Menu.ROOT, name, DEFAULT_ACTION);
    }

    public void addChildrenItem(String parentName, String childName) {
        menu.add(parentName, childName, DEFAULT_ACTION);
    }

    public void executeAction(String itemName) {
        menu.select(itemName).ifPresent(itemInfo -> itemInfo.getActionDelegate().delegate());
    }

    public void printMenu() {
        MenuPrinter printer = new Printer();
        printer.print(menu);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        TodoApp app = new TodoApp(menu);

        while (true) {
            System.out.println("1. Добавить элемент в корень");
            System.out.println("2. Добавить элемент к родительскому элементу");
            System.out.println("3. Вызвать действие по пункту меню");
            System.out.println("4. Вывести меню");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя элемента: ");
                    String rootItem = scanner.nextLine();
                    app.addRootItem(rootItem);
                    break;
                case 2:
                    System.out.print("Введите имя родительского элемента: ");
                    String parentItem = scanner.nextLine();
                    System.out.print("Введите имя дочернего элемента: ");
                    String childItem = scanner.nextLine();
                    app.addChildrenItem(parentItem, childItem);
                    break;
                case 3:
                    System.out.print("Введите имя пункта меню для вызова действия: ");
                    String actionItem = scanner.nextLine();
                    app.executeAction(actionItem);
                    break;
                case 4:
                    app.printMenu();
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}


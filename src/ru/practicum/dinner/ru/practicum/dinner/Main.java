package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Scanner scanner;
    static Random random;
    static DinnerConstructor dinnerConstructor;

    public static void main(String[] args) {
        dinnerConstructor = new DinnerConstructor();
        scanner = new Scanner(System.in);
        random = new Random();

        dinnerConstructor.setDefaultDishes();

        while (true) {
            printMenu();
            String command = scanner.nextLine().trim();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Выберите команду из списка");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        dinnerConstructor.setDishes(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите " +
                        "пустую строку.\nВ настоящее время доступны для выбора следующие категории (типы) блюд: %s \n",
                dinnerConstructor.dishes.keySet());

        String nextItem = scanner.nextLine();
        ArrayList<String> inputtedItems = new ArrayList<>();
        while (!nextItem.isEmpty()) {
            if (dinnerConstructor.dishes.containsKey(nextItem)) {
                inputtedItems.add(nextItem);
            } else {
                System.out.println("Данный тип блюд отсутствует во введенном ранее перечне.");
            }
            nextItem = scanner.nextLine();
        }
        System.out.printf("Выбраны следующие категории блюд: %s \n", inputtedItems);
        dinnerConstructor.combosConstructor(inputtedItems, numberOfCombos);
    }
}
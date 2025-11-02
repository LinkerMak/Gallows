package org.example.game;

import java.util.*;

public class Gallows {
    private int errors = 0;
    private String word;
    private StringBuilder mask;
    private GallowsInputChecker inputChecker;
    private List<Character> predictLetters;

    public Gallows(String word) {
        this.word = word.toLowerCase();

        mask = new StringBuilder(word.length());
        for(int i = 0; i < word.length(); i++) {
            mask.append('*');
        }

        predictLetters = new ArrayList<>();
        inputChecker = new GallowsInputChecker(word, mask, predictLetters);
    }

    public int startGame() {
        System.out.println("Игра началась!. Чтобы выиграть введите слово полностью или можете отгадывать буквы :)");

        while (errors != 5) {
            if(stage() == 1) return 1;
        }

        return 0;
    }

    private int stage() {
        System.out.println("-----------------------");
        System.out.println("Ваше слово:" + mask);
        int result = input();

        switch(result) {
            case 1 -> {
                return 1;
            }
            case -1 -> {
                printHangman();
                errors++;
            }
        }

        System.out.print("Вы использовали буквы: ");
        for (Character letter : predictLetters) {
            System.out.print(letter + " ");
        }
        System.out.println();

        return 0;
    }

    private int input() {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        while(result == 0) {
            System.out.println("Введите ответ или букву:");
            result = inputChecker.check(scanner.nextLine().toLowerCase());
        }

        return result;
    }

    private void printHangman() {
        System.out.println("У вас осталось " + (4 - errors) + " ошибок");
        switch(errors) {
            case 0 -> {
                System.out.println(" ║         ║");
                System.out.println(" ║         O");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
            }
            case 1 -> {
                System.out.println(" ║         ║");
                System.out.println(" ║         O");
                System.out.println(" ║         │");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
            }
            case 2 -> {
                System.out.println(" ║         ║");
                System.out.println(" ║         O");
                System.out.println(" ║        /│\\");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
            }
            case 3 -> {
                System.out.println(" ║         ║");
                System.out.println(" ║         O");
                System.out.println(" ║        /│\\");
                System.out.println(" ║        /");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
            }
            case 4 -> {
                System.out.println(" ║         ║");
                System.out.println(" ║         O");
                System.out.println(" ║        /│\\");
                System.out.println(" ║        / \\");
                System.out.println(" ║");
                System.out.println(" ║");
                System.out.println(" ║");
            }
        }
    }
}

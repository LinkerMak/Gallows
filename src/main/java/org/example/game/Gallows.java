package org.example.game;

import java.util.*;

public class Gallows {
    private int errors = 0;
    private String word;
    private Set<Character> letters;
    private StringBuilder mask;
    private List<Character> predictLetters;

    public Gallows(String word) {
        this.word = word.toLowerCase();

        predictLetters = new ArrayList<>();
        letters = new HashSet();
        mask = new StringBuilder();
        for(char ch : this.word.toCharArray()) {
            mask.append('*');
            letters.add(ch);
        }

    }

    public int startGame() {
        System.out.println("Игра началась!. Чтобы выиграть введите слово полностью или можете отгадывать буквы :)");

        while (errors != 5) {
            if(stage() == -1) return -1;
        }

        return 0;
    }

    private int stage() {
        System.out.println("Ваше слово:" + mask);
        System.out.println("Введите букву:");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toLowerCase();

        if(str.equals(this.word)) {
            return -1;
        }
        else if(str.length() > 1) {
            System.out.println("Неправильно");
            printHangman();
            errors++;
        }
        else if(str.length() == 1) {
            char ch = str.charAt(0);
            if(letters.contains(ch)) {
                for(int i = 0; i < word.length();i++) {
                    if(word.charAt(i) == ch) {
                        mask.setCharAt(i, ch);
                    }
                }
            }
            else{
                System.out.println("Упс, такой буквы нет!");
                printHangman();
                errors++;
            }

            predictLetters.add(ch);
            System.out.println("Вы использовали буквы: ");
            for (Character letter : predictLetters) {
                System.out.print(ch + " ");
            }
        }

        return 0;
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

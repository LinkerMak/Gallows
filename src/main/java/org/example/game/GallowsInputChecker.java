package org.example.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GallowsInputChecker {

    private String word;
    private List<Character> predictLetters;
    private StringBuilder mask;
    private Set<Character> errorLetters;

    public GallowsInputChecker(String word, StringBuilder mask, List<Character> predictLetters) {
        this.word = word;
        this.predictLetters = predictLetters;
        this.mask = mask;
        this.errorLetters = new HashSet<>();
    }

    public int check(String input) {

        if(input.equals(this.word)) {
            return 1;
        }
        else if(input.length() != 1) {
            if(input.length() == word.length()) {
                System.out.println("Неправильная попытка отгадывания слова");
                return -1;
            }
            System.out.println("Некорректный ввод");
            return 0;
        }
        else {

            if(!isRussian(input)) {
                System.out.println("Некорректный ввод");
                return 0;
            }

            char ch = input.charAt(0);
            if(errorLetters.contains(ch)) {
                System.out.println("Вы уже использовали эту букву и такой буквы нет");
                return 0;
            }
            predictLetters.add(ch);

            if(word.contains(input)) {
                for(int i = 0; i < word.length();i++) {
                    if(word.charAt(i) == ch) {
                        mask.setCharAt(i,ch);
                    }
                }
                System.out.println("Такая буква есть");
                return 2;
            }
            else{
                errorLetters.add(ch);
                System.out.println("Такой буквы нет");
                return -1;
            }

        }

    }

    public boolean isRussian(String input) {

        char ch = input.charAt(0);
        if(ch >= 'а' && ch <= 'я' || ch == 'ё' ) {
            return true;
        }

        return false;
    }
}

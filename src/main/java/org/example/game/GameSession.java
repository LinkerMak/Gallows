package org.example.game;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class GameSession {

    public static void main(String[] args) throws IOException {
        GameSession gameSession = new GameSession("HARD");
        gameSession.startSession();
    }

    private static final String FILE_FOR_D3 = "src/main/resources/dictionary_7.txt";
    private static final String FILE_FOR_D2 = "src/main/resources/dictionary_5.txt";
    private static final String FILE_FOR_D1 = "src/main/resources/dictionary_3.txt";

    private String file;

    public GameSession(String difficult) {
        switch (difficult){
            case "HARD" -> file = FILE_FOR_D3;
            case "medium" -> file = FILE_FOR_D2;
            case "easy" -> file = FILE_FOR_D1;
        }
    }

    public void startSession() throws IOException {

        while(true) {
            System.out.println("[Н]овая игра или [В]ыход?");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if(answer.equals("Н")) {
                String word = takeRandomWord(file);
                Gallows gallows = new Gallows(word);
                if(gallows.startGame() == 0) {
                    System.out.println("Вы проиграли, ваше слово было:" + word);
                }
                else {
                    System.out.println("Поздравляем! Вы победили");
                }
            }else if(answer.equals("В")) {
                break;
            }
            else {
                System.out.println("Пожалуйста, введите корректную команду");
            }
        }

    }



    private String takeRandomWord(String inputFile) throws IOException {
        Path path = Paths.get(inputFile);

        if(!Files.exists(path)) {
            throw new FileNotFoundException("Файл не найден:" + path);
        }

        Stream<String> stream = Files.lines(path);
        List<String> words = stream.map(word -> word.trim()).toList();

        Random random = new Random();
        System.out.println("Всего слов: " + words.size());
        return words.get(random.nextInt(words.size()));
    }
}

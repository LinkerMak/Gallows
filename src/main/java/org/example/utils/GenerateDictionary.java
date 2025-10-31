package org.example.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://github.com/Harrix/Russian-Nouns/blob/main/dist/russian_nouns.txt
public class GenerateDictionary {

    private static final short JUMP = 3;

    public static void main(String[] args) throws IOException {
        String inputFile = "src/main/resources/russian_nouns.txt";
        String outputFile = "src/main/resources/dictionary_" + JUMP + ".txt";

        streamFilterAndSaveFile(inputFile, outputFile);
        //List<String> words = readFileAndFilter(inputFile);
        //writeFile(outputFile, words);
    }

    public static List<String> readFileAndFilter(String inputFile) throws IOException {
        List<String> resultList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(inputFile));

        String temp;
        String[] tempArr;
        while( (temp = br.readLine()) != null) {
           tempArr = temp.split("\n");

           for(String str : tempArr) {
               if(str.length() > JUMP) {
                   resultList.add(str);
               }
           }
        }

        return resultList;
    }

    public static void writeFile(String outputFile, List<String> words) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        for(String word : words) {
            bw.write(word);
            bw.newLine();
        }
    }

    public static void streamFilterAndSaveFile(String inputFile, String outputFile) throws IOException {
        Path inputPath = Paths.get(inputFile);
        Path outputPath = Paths.get(outputFile);

        if(!Files.exists(inputPath)) {
            throw new FileNotFoundException("Файл не найден:" + inputPath);
        }

        Stream<String> stream = Files.lines(inputPath);
        List<String> words = stream
                .filter(word -> word.length() > JUMP)
                .toList();

        Files.write(outputPath, words);
    }
}

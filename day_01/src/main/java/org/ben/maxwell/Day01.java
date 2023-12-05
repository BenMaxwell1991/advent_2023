package org.ben.maxwell;

import com.google.common.collect.ImmutableMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Path.of;

public class Day01 {
    public static void main(String[] args) throws IOException {

        solvePart1();
        solvePart2();

    }

    static void solvePart1() throws IOException {

        List<String> input = readAllLines(of("day_01","src", "main", "resources", "input_01.txt"));
        List<Integer> values = new ArrayList<>();

        for (String str : input) {
            String first = Character.toString((char) str.chars().filter(Character::isDigit).findFirst().orElseGet(() -> 0));
            String last = Character.toString((char) str.chars().filter(Character::isDigit).reduce((a, b) -> b).orElseGet(() -> 0));
            values.add(valueOf(first + last));
        }

        Integer total = values.stream().reduce(Integer::sum).get();
        System.out.println(total);

    }

    static void solvePart2() throws IOException {

        try (InputStream inputStream = Day01.class.getClassLoader().getResourceAsStream("input_01.txt")) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> input = reader.lines().toList();

            List<Integer> values = new ArrayList<>();

            for (String str : input) {

                List<String> numbersInString = new ArrayList<>();

                for (int i = 0; i < str.length(); i++) {
                    if (Character.isDigit(str.charAt(i))) {
                         numbersInString.add(Character.toString(str.charAt(i)));
                    } else {
                        for (String digit : Digits.keySet()) {
                            if (str.substring(i).startsWith(digit)) {
                                numbersInString.add(Digits.get(digit));
                            }
                        }
                    }
                }

                String first = numbersInString.get(0);
                String last = numbersInString.get(numbersInString.size() - 1);

                values.add(valueOf(first + last));

            }

            Integer total = values.stream().reduce(Integer::sum).get();
            System.out.println(total);

        }

    }

    public static ImmutableMap<String, String> Digits = new ImmutableMap.Builder<String, String>()
            .put("zero", "0")
            .put("one", "1")
            .put("two", "2")
            .put("three", "3")
            .put("four", "4")
            .put("five", "5")
            .put("six", "6")
            .put("seven", "7")
            .put("eight", "8")
            .put("nine", "9")
            .build();

}
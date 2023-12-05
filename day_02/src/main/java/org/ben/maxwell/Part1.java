package org.ben.maxwell;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Path.of;

public class Part1 {

    static void solve() throws Exception {

        List<String> input = readAllLines(of("day_02","src", "main", "resources", "input_01.txt"));

        int total = input.stream()
                .filter(Part1::isGameValid) // Assuming isGameValid is a method in the same class
                .mapToInt(game -> parseInt(game.substring(5, game.indexOf(":"))))
                .sum();

        System.out.println(total);

    }

    static boolean isGameValid(String game) {

        List<String> turns = List.of(game.substring(game.indexOf(":") + 2).split("; "));

        for (String turn : turns) {

            if (!isTurnValid(turn)) {

                return false;

            }

        }

        return true;

    }

    static boolean isTurnValid(String turn) {

        final int MAX_R = 12;
        final int MAX_G = 13;
        final int MAX_B = 14;

        Integer r = 0;
        Integer g = 0;
        Integer b = 0;

        List<String> splitTurn = List.of(turn.split(", "));

        for (String colour: splitTurn) {

            int quantity = Integer.parseInt(colour.split(" ")[0]);

            switch (colour.split(" ")[1]) {
                case "red" -> r = quantity;
                case "green" -> g = quantity;
                case "blue" -> b = quantity;
                default -> {}
            }

        }

        return r <= MAX_R && g <= MAX_G && b <= MAX_B;

    }

}

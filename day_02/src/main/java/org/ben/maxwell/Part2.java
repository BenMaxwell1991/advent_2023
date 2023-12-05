package org.ben.maxwell;

import java.util.List;

import static java.lang.Integer.max;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Path.of;

public class Part2 {

    static void solve() throws Exception {

        List<String> input = readAllLines(of("day_02","src", "main", "resources", "input_01.txt"));

        int totalPower = 0;

        for (String game: input) {

            int maxR = 0;
            int maxG = 0;
            int maxB = 0;

            String[] allTurns = game.substring(game.indexOf(":") + 2).split("; ");

            for (String turn : allTurns) {

                 String[] allColours = turn.split(", ");

                 for(String colour : allColours) {

                     int quantity = Integer.parseInt(colour.split(" ")[0]);

                     switch (colour.split(" ")[1]) {
                         case "red" -> maxR = max(maxR, quantity);
                         case "green" -> maxG = max(maxG, quantity);
                         case "blue" -> maxB = max(maxB, quantity);
                         default -> {}
                     }

                 }

            }

            totalPower += maxR * maxG * maxB;

        }

        System.out.println(totalPower);

    }

}

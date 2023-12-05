package org.ben.maxwell;

import org.ben.maxwell.Day03.Gear;
import org.ben.maxwell.Day03.PartNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static java.lang.Character.isDigit;

public class Part2 {

    static void solve(List<PartNumber> numbers, List<Gear> gears, List<String> input) {

        for (PartNumber number : numbers) {

            // Find all gears adjacent to this part and assign this part to them
            List<Gear> localGears = findAdjacentGears(number, input);

            for (Gear localGear : localGears) {

                // Find the corresponding gear in my global List and store the adjacentPartNumber information
                gears.stream()
                        .filter(existingGear -> existingGear.index == localGear.index && existingGear.line == localGear.line)
                        .findFirst()
                        .ifPresent(existingGear -> existingGear.adjacentPartNumbers.addAll(localGear.adjacentPartNumbers));

            }

        }

        // Sum the values of the gears that only have two adjacent parts accordingly.
        int total = gears.stream()
                .filter(gear -> gear.adjacentPartNumbers.size() == 2)
                .mapToInt(gear -> gear.adjacentPartNumbers.get(0).value * gear.adjacentPartNumbers.get(1).value)
                .sum();

        System.out.println(total);

    }

    // Given a PartNumber, find any adjacent gears
    static List<Gear> findAdjacentGears(PartNumber number, List<String> input) {

        List<Gear> gears = new ArrayList<>();

        int maxLength = input.get(0).length();

        // Check line above
        if (number.line > 0) {

            String checkUp = input.get(number.line - 1);

            int min = Math.max(0, number.index - 1);
            int max = Math.min(number.index + number.length + 1, checkUp.length());

            IntStream.range(min, max).forEachOrdered(i -> {
                addGear(checkUp.charAt(i), number.line - 1, i, number, gears);
            });

        }

        // Check to the left
        if (number.index > 0) {

            char c = input.get(number.line).charAt(number.index - 1);
            addGear(c, number.line, number.index - 1, number, gears);

        }

        // Check to right
        if (number.index + number.length < maxLength) {

            char c = input.get(number.line).charAt(number.index + number.length);
            addGear(c, number.line, number.index + number.length, number, gears);

        }

        // Criss-cross, I mean check below.
        if (number.line < input.size() - 1) {

            String checkDown = input.get(number.line + 1);

            int min = Math.max(0, number.index - 1);
            int max = Math.min(number.index + number.length + 1, checkDown.length());

            IntStream.range(min, max).forEachOrdered(i -> {
                addGear(checkDown.charAt(i), number.line + 1, i, number, gears);
            });

        }

        return gears;

    }

    static void addGear(Character character, int line, int index, PartNumber number, List<Gear> gears) {

        IntPredicate isGear = c -> c == '*';

        if (isGear.test(character)) {
            Gear gear = new Gear();
            gear.line = line;
            gear.index = index;
            gear.adjacentPartNumbers.add(number);
            gears.add(gear);
        }

    }

}

package org.ben.maxwell;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Path.of;

public class Day03 {

    public static void main(String[] args) throws Exception {

        List<String> input = readAllLines(of("day_03","src", "main", "resources", "input_01.txt"));
        List<PartNumber> partNumbers = getPartNumbers(input);
        List<Gear> gears = getGears(input);

        Part1.solve(partNumbers, input);
        Part2.solve(partNumbers, gears, input);

    }

    // Compile a list of all the PartNumbers in the data.
    static List<PartNumber> getPartNumbers(List<String> input) {

        List<PartNumber> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");

        for (int i = 0; i < input.size(); i++) {

            String line = input.get(i);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                int numberValue = Integer.parseInt(matcher.group());
                PartNumber number = new PartNumber();
                number.value = numberValue;
                number.line = i;
                number.index = matcher.start();
                number.length = matcher.group().length();
                numbers.add(number);
            }

        }

        return numbers;

    }

    static List<Gear> getGears(List<String> input) {

        List<Gear> gears = new ArrayList<>();
        Pattern gearPattern = Pattern.compile("\\*");

        for (int i = 0; i < input.size(); i++) {

            String line = input.get(i);
            Matcher gearMatcher = gearPattern.matcher(line);

            while (gearMatcher.find()) {
                Gear gear = new Gear();
                gear.line = i;
                gear.index = gearMatcher.start();
                gears.add(gear);
            }

        }

        return gears;
    }

    // Store all the exciting information for each number in the data, everything we need for validation.
    static class PartNumber {

        int value;
        int line;
        int index;
        int length;

    }

    static class Gear {

        int line;
        int index;
        List<PartNumber> adjacentPartNumbers = new ArrayList<>();

    }

}
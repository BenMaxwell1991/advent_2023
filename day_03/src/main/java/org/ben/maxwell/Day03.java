package org.ben.maxwell;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Path.of;

public class Day03 {

    public static void main(String[] args) throws Exception {

        List<String> input = readAllLines(of("day_03","src", "main", "resources", "input_01.txt"));
        List<Number> numbers = getNumbers(input);

        int total = numbers.stream()
                .filter(number -> isNumberValid(number, input))
                .mapToInt(number -> number.value)
                .sum();

        System.out.println(total);

    }

    // Given a Number, use the input data to check it's validity.
    static boolean isNumberValid(Number number, List<String> input) {

        int maxLength = input.get(0).length();
        boolean isValid = false;
        IntPredicate isSymbol = character -> !isDigit(character) && character != '.';

        // Check line above
        if (number.line > 0) {

            String checkUp = input.get(number.line - 1);

            int min = Math.max(0, number.index - 1);
            int max = Math.min(number.index + number.length + 1, checkUp.length());

            isValid = checkUp.substring(min, max).chars().anyMatch(isSymbol);

        }

        // Check to the left
        if (number.index > 0) {

            char c = input.get(number.line).charAt(number.index - 1);
            isValid = isValid || isSymbol.test(c);

        }

        // Check to right
        if (number.index + number.length < maxLength) {

            char c = input.get(number.line).charAt(number.index + number.length);
            isValid = isValid || isSymbol.test(c);

        }

        // Criss-cross, I mean check below.
        if (number.line < input.size() - 1) {

            String checkDown = input.get(number.line + 1);

            int min = Math.max(0, number.index - 1);
            int max = Math.min(number.index + number.length + 1, checkDown.length());

            isValid = isValid || checkDown.substring(min, max).chars().anyMatch(isSymbol);

        }

        return isValid;

    }

    // Compile a list of all the numbers in the data.
    static List<Number> getNumbers(List<String> input) {

        List<Number> numbers = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+");

        for (int i = 0; i < input.size(); i++) {

            String line = input.get(i);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                int numberValue = Integer.parseInt(matcher.group());
                Number number = new Number();
                number.value = numberValue;
                number.line = i;
                number.index = matcher.start();
                number.length = matcher.group().length();
                numbers.add(number);
            }

        }

        return numbers;

    }

    // Store all the exciting information for each number in the data, everything we need for validation.
    static class Number {

        int value;
        int line;
        int index;
        int length;

    }

}
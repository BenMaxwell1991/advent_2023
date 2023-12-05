package org.ben.maxwell;

import org.ben.maxwell.Day03.PartNumber;

import java.util.List;
import java.util.function.IntPredicate;

import static java.lang.Character.isDigit;

public class Part1 {

    static void solve(List<PartNumber> numbers, List<String> input) {

        int total = numbers.stream()
                .filter(number -> isNumberValid(number, input))
                .mapToInt(number -> number.value)
                .sum();

        System.out.println(total);

    }

    // Given a PartNumber, use the input data to check its validity.
    static boolean isNumberValid(PartNumber number, List<String> input) {

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

}

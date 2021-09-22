package jan.truksa.piglatin.conversion;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface PigLatinRuleSet {

    String WAY = "way";
    String AY = "ay";

    static String applyRules(final String input) {
        // Solve hyphens
        if (input.contains("-")) {
            return Arrays.stream(input.split("-"))
                    .map(PigLatinRuleSet::applyRules)
                    .collect(Collectors.joining("-"));
        }

        // Solve ends with way
        if (input.toLowerCase(Locale.ROOT).endsWith(WAY)) {
            return input;
        }

        final StringBuilder result = solveCapitalization(
                removePunctuation(input.chars()),
                removePunctuation(solveVowelsAndConsonants(input).chars())
        );

        solvePunctuation(input, result);

        return result.toString();
    }

    static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    static StringBuilder solveVowelsAndConsonants(final String input) {
        StringBuilder output = new StringBuilder(input);
        if (isVowel(input.charAt(0))) {
            output.append(WAY);
        } else if (!isVowel(input.charAt(0))) {
            char firstChar = output.charAt(0);
            output.deleteCharAt(0).append(firstChar).append(AY);
        }
        return output;
    }

    static StringBuilder solveCapitalization(final StringBuilder inputWithoutPunctuation, final StringBuilder result) {
        IntStream.range(0, inputWithoutPunctuation.length()).forEach(position -> {
            boolean isOriginalUpperCase = Character.isUpperCase(inputWithoutPunctuation.charAt(position));
            char resultCharacter = result.charAt(position);
            if (Character.isUpperCase(resultCharacter) != isOriginalUpperCase) {
                result.setCharAt(position, isOriginalUpperCase ? Character.toUpperCase(resultCharacter) : Character.toLowerCase(resultCharacter));
            }
        });
        return result;
    }

    static StringBuilder removePunctuation(final IntStream chars) {
        return chars.mapToObj(c -> (char) c).filter(Character::isLetter).collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append));
    }

    static void solvePunctuation(final String input, final StringBuilder result) {
        int sizeDifference = result.length() - input.length();
        for (int resultPosition = result.length(); resultPosition > result.length() - input.length(); resultPosition--) {
            char charAtActualInputPosition = input.charAt(resultPosition - sizeDifference - 1);
            if (!Character.isLetter(charAtActualInputPosition)) {
                result.insert(resultPosition, charAtActualInputPosition);
            }
        }
    }

}

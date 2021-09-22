package jan.truksa.piglatin.conversion;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PigLatinConverter implements Function<String, String> {

    private static final String WAY = "way";
    private static final String AY = "ay";

    @Override
    public String apply(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        return Arrays.stream(s.split("\\s+")).map(this::applyRules).collect(Collectors.joining(" "));
    }

    private String applyRules(String input) {
        // Solve hyphens
        if (input.contains("-")) {
            return Arrays.stream(input.split("-")).map(this::applyRules).collect(Collectors.joining("-"));
        }

        // Solve ends with way
        if (input.toLowerCase(Locale.ROOT).endsWith(WAY)) {
            return input;
        }

        String output = input;
        if (isVowel(input.charAt(0))) {
            output = input + WAY;
        } else if (!isVowel(input.charAt(0))) {
            output = input.substring(1) +Character.toLowerCase(input.charAt(0)) + AY;
        }

        Character[] outputCharArray = output.chars().mapToObj(c -> (char)c).filter(character -> Character.isLetter(character)).toArray(Character[]::new);

        StringBuilder result = new StringBuilder();
        int i = 0;
        for(; i < input.length(); i++) {
            boolean originalIsUpperCase = Character.isUpperCase(input.charAt(i));

            if (Character.isUpperCase(outputCharArray[i]) != originalIsUpperCase){
                result.append(originalIsUpperCase ? Character.toUpperCase(outputCharArray[i] ) : Character.toLowerCase(outputCharArray[i] ));
            } else {
                result.append(outputCharArray[i]);
            }
        }

        for(; i < outputCharArray.length; i++) {
            result.append(outputCharArray[i]);
        }

        int diference = outputCharArray.length-input.length();
        for (int j = outputCharArray.length; j > outputCharArray.length-input.length(); j--) {
            if (!Character.isLetter(input.charAt(j-diference-1))) {
                result.insert(j,input.charAt(j-diference-1));
            }

        }

        return result.toString();
    }

    public static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }


}

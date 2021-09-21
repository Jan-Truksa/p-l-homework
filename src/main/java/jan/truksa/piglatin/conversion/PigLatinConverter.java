package jan.truksa.piglatin.conversion;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PigLatinConverter implements Function<String, String> {

    private static final String WAY = "way";
    private static final String AY = "ay";

    @Override
    public String apply(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        return Arrays.stream(s.split(" ")).map(this::applyRules).collect(Collectors.joining(" "));
    }

    private String applyRules(String input) {
        if (input.contains("-")) {
            return Arrays.stream(input.split("-")).map(this::applyRules).collect(Collectors.joining("-"));
        }
        String output = input;
        if (input.toLowerCase(Locale.ROOT).endsWith(WAY)) {
            return output;
        } else if (isVowel(input.charAt(0))) {
            output = input + WAY;
        } else if (!isVowel(input.charAt(0))) {
            output = input.substring(1) +Character.toLowerCase(input.charAt(0)) + AY;
        }

        char[] outputCharArray = output.toCharArray();

        // TODO: remove the non charcters and add them later
        //char[] outputCharArray = output.chars().mapToObj(c -> (char)c).filter(character -> !Character.isLetter(character));

        for(int i = 0; i < input.length(); i++) {
            boolean originalIsUpperCase = Character.isUpperCase(input.charAt(i));
            if (Character.isUpperCase(outputCharArray[i]) != originalIsUpperCase){
                outputCharArray[i] = originalIsUpperCase ? Character.toUpperCase(outputCharArray[i] ) : Character.toLowerCase(outputCharArray[i] );
            }
        }


        return String.valueOf(outputCharArray);
    }

    public static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }


}

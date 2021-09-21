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
        return Arrays.stream(s.split(" ")).map(this::applyRules).collect(Collectors.joining(" "));
    }

    private String applyRules(String input) {
        if (input.toLowerCase(Locale.ROOT).endsWith(WAY)) {
            return input;
        } else if (isVowel(input.charAt(0))) {
            return input + WAY;
        } else if (!isVowel(input.charAt(0))) {
            return input.substring(1) + input.charAt(0) + AY;
        }
        return "";
    }

    public static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }


}

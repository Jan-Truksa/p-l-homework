package jan.truksa.piglatin.conversion;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class PigLatinConverter implements Function<String, String> {

    @Override
    public String apply(String s) {
        if (isBlank(s)) {
            return s;
        }

        return Arrays.stream(s.split("\\s+"))
                .map(PigLatinRuleSet::applyRules)
                .collect(Collectors.joining(" "));
    }

}

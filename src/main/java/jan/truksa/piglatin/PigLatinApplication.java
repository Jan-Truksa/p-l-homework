package jan.truksa.piglatin;

import jan.truksa.piglatin.conversion.PigLatinConverter;

public class PigLatinApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("There should be exactly one string parameter defined.");
            System.exit(0);
        }

        System.out.println(new PigLatinConverter().apply(args[0]));
    }

}
